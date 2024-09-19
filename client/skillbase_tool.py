"""Skillbase Tool"""

import argparse
import sys
from typing import Any, TextIO

from easydict import EasyDict
from mods.skillbase_cmnd_project import skillbase_cmnd_project
from mods.skillbase_cmnd_version import skillbase_cmnd_version
from mods.skillbase_config import SkillbaseConfig
from mods.skillbase_context import SkillbaseContext
from mods.skillbase_util import (
    skillbase_util_trace_print,
    skillbase_util_trace_set_enabled,
    skillbase_util_trace_set_output,
)

_skillbase_tool_dispatch: EasyDict = EasyDict(
    {
        "project": skillbase_cmnd_project,
        "version": skillbase_cmnd_version,
    }
)


def _skillbase_tool_cmnd(args: EasyDict, stdout: TextIO) -> int:
    """TBD"""
    try:
        # Setup tracing
        skillbase_util_trace_set_output(stdout)
        skillbase_util_trace_set_enabled(True)

        # Load the config file
        config: SkillbaseConfig = SkillbaseConfig.load(args.config)

        # Create a context for the command
        context: SkillbaseContext = SkillbaseContext(config, args)

        # config.trace(context)

        # If a command was specified, call the handler
        if args.cmnd is not None:
            return _skillbase_tool_dispatch[args.cmnd](context)

        # No command, just exit
        return 0

    except RuntimeError:
        skillbase_util_trace_print(sys.exc_info()[1])
        return 1


def _skillbase_tool_formatter(prog: Any) -> argparse.HelpFormatter:
    """TBD"""
    return argparse.HelpFormatter(prog, max_help_position=40, width=130)


def _skillbase_tool_args() -> argparse.Namespace:
    """TBD"""

    parser: argparse.ArgumentParser = argparse.ArgumentParser(
        prog="skillbase_tool.py", formatter_class=_skillbase_tool_formatter
    )
    parser.add_argument(
        "-c",
        "--config",
        required=True,
        type=str,
        help="config file",
    )

    subcmds: argparse._SubParsersAction = parser.add_subparsers(dest="cmnd")

    # Setup Project command
    subcmd_project: argparse.ArgumentParser = subcmds.add_parser(
        "project", help="build project", formatter_class=_skillbase_tool_formatter
    )
    subcmd_project.add_argument(
        "--inp-filename",
        required=True,
        type=str,
        action="store",
        help="definition file name (.def)",
    )

    # Setup Version command
    subcmds.add_parser(
        "version", help="version info", formatter_class=_skillbase_tool_formatter
    )

    # Parse the arguments
    return parser.parse_args()


def skillbase_tool_main() -> int:
    """TBD"""
    return _skillbase_tool_cmnd(_skillbase_tool_args(), sys.stdout)


if __name__ == "__main__":
    sys.exit(skillbase_tool_main())
