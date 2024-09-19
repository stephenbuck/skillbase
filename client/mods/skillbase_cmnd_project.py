"""Skillbase Cmnd Project"""

import traceback

from easydict import EasyDict
from .skillbase_context import SkillbaseContext
from .skillbase_test_catalog_categories import skillbase_test_catalog_categories
from .skillbase_test_catalog_credentials import skillbase_test_catalog_credentials
from .skillbase_test_catalog_skills import skillbase_test_catalog_skills
from .skillbase_util import (
    skillbase_util_loadjson,
    skillbase_util_timer_enter,
    skillbase_util_timer_leave,
)

_skillbase_project_dispatch_table = EasyDict(
    {
        "test_catalog_categories": skillbase_test_catalog_categories,
        "test_catalog_credentials": skillbase_test_catalog_credentials,
        "test_catalog_skills": skillbase_test_catalog_skills,
    }
)


class _SkillbaseProjectDef:
    """Skillbase Project: Def"""

    def __init__(self, options: dict, commands: list):
        self.options = options
        self.commands = commands

    @classmethod
    def load(cls, inp_filepath: str, inp_varsdict: dict):
        return _SkillbaseProjectDef.parse(
            skillbase_util_loadjson(inp_filepath, inp_varsdict)
        )

    @classmethod
    def parse(cls, json_project: dict):
        return _SkillbaseProjectDef(
            _SkillbaseProjectDefOptions.parse(json_project.get("options")),
            _SkillbaseProjectDefCommands.parse(json_project.get("commands")),
        )

    def trace(self, context: SkillbaseContext) -> None:
        context.tracer_enter("def")
        self.options.trace(context)
        self.commands.trace(context)
        context.tracer_leave()


class _SkillbaseProjectDefOptions:
    """Skillbase Project: Def Options"""

    def __init__(self, options: dict):
        self.options = options

    @classmethod
    def parse(cls, json_options: dict):
        return _SkillbaseProjectDefOptions(json_options)

    def trace(self, context: SkillbaseContext) -> None:
        context.tracer_enter("options")
        context.tracer_leave()


class _SkillbaseProjectDefCommands:
    """Skillbase Project: Def Commands"""

    def __init__(self, commands: list):
        self.commands = commands

    @classmethod
    def parse(cls, json_commands: list):
        return (
            None
            if json_commands is None
            else _SkillbaseProjectDefCommands(
                [
                    _SkillbaseProjectDefCommand.parse(json_command)
                    for json_command in json_commands
                ]
            )
        )

    def trace(self, context: SkillbaseContext) -> None:
        context.tracer_enter("commands")
        for index, command in enumerate(self.commands):
            command.trace(index, context)
        context.tracer_leave()


class _SkillbaseProjectDefCommand:
    """Skillbase Project: Def Command"""

    def __init__(self, cmnd: str, note: str, args: dict):
        self.cmnd = cmnd
        self.note = note
        self.args = args

    @classmethod
    def parse(cls, json_command: dict):
        return (
            None
            if json_command is None
            else _SkillbaseProjectDefCommand(
                json_command.get("cmnd"),
                json_command.get("note"),
                json_command.get("args"),
            )
        )

    def trace(self, index: int, context: SkillbaseContext) -> None:
        context.tracer_enter(f"command ({index})")
        context.tracer_print(f"cmnd    = {self.cmnd}")
        context.tracer_print(f"note    = {self.note}")
        context.tracer_print(f"args    = {self.args}")
        context.tracer_leave()


def _skillbase_project_dispatch(context: SkillbaseContext, cmnd: str) -> int:
    """Skillbase Project: Dispatch"""
    if _skillbase_project_dispatch_table.get(cmnd) is None:
        raise Exception(f"Unknown command '{cmnd}'")
    return _skillbase_project_dispatch_table[cmnd](context)


def _skillbase_project_command(
    context: SkillbaseContext,
    options: _SkillbaseProjectDefOptions,
    command: _SkillbaseProjectDefCommand,
) -> int:
    """Skillbase Project: Command"""
    try:
        context.tracer_enter("command")

        context.tracer_enter("args")
        context.tracer_print(f"cmnd = {command.cmnd}")
        context.tracer_print(f"note = {command.note}")
        context.tracer_print(f"args = {command.args}")
        context.tracer_leave()

        xargs: EasyDict = EasyDict({"cmnd": command.cmnd})

        invoke: SkillbaseContext = SkillbaseContext(xargs)

        return _skillbase_project_dispatch(invoke, command.cmnd)

    finally:
        context.tracer_leave()


def skillbase_cmnd_project(context: SkillbaseContext) -> int:
    """TBD"""
    try:
        context.tracer_enter("project")

        args: EasyDict = EasyDict(
            {"inp_filename": context.getarg("inp_filename", None)}
        )

        context.tracer_enter("args")
        context.tracer_print(f"inp_filename = {args.inp_filename}")
        context.tracer_leave()

        # Load the definition (a JSON file)
        def_project: _SkillbaseProjectDef = _SkillbaseProjectDef.load(
            context.args.inp_filename, {}
        )
        def_project.trace(context)

        def_options: _SkillbaseProjectDefOptions = def_project.options
        def_commands: _SkillbaseProjectDefCommands = def_project.commands

        # Start timer
        enter: float = skillbase_util_timer_enter()

        # Execute each command
        for def_command in def_commands.commands:
            if not def_command.cmnd.startswith("skip-"):
                try:
                    _skillbase_project_command(context, def_options, def_command)
                except Exception:
                    context.tracer_print(
                        traceback.format_exc()
                    )  # BOZO sys.exc_info()[1])

        # End timer
        elapsed: float = skillbase_util_timer_leave(enter)
        context.tracer_print(f"Project Time: {elapsed:.2f} sec.")

        return 0

    finally:
        context.tracer_leave()
