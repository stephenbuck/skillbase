"""Skillbase Util"""

import inspect
import json
import sys
import time
from subprocess import STDOUT, check_output
from typing import Any, AnyStr, TextIO

from jinja2 import Template

# -------------------------------------------------------------
# Skillbase Util: Trace
# -------------------------------------------------------------


class SkillbaseUtilTrace:
    """TBD"""

    def __init__(self, enabled: bool, output: TextIO):
        """TBD"""
        self.enabled = enabled
        self.output = output
        self.padding = " "
        self.indent = 4
        self.level = 0

    def get_enabled(self) -> bool:
        """TBD"""
        return self.enabled

    def set_enabled(self, enabled: bool) -> None:
        """TBD"""
        self.enabled = enabled

    def get_output(self) -> TextIO:
        """TBD"""
        return self.output

    def set_output(self, output: TextIO):
        """TBD"""
        original = self.output
        self.output = output
        return original

    def get_padding(self) -> int:
        """TBD"""
        return self.padding

    def set_padding(self, padding: int) -> None:
        """TBD"""
        self.padding = padding

    def get_indent(self) -> int:
        """TBD"""
        return self.indent

    def set_indent(self, indent: int):
        """TBD"""
        self.indent = indent

    def get_level(self) -> int:
        """TBD"""
        return self.level

    def set_level(self, level: int) -> None:
        """TBD"""
        self.level = level

    def enter(self, title: str) -> None:
        """TBD"""
        if self.enabled:
            self.print(title)
            self.print("{")
            self.level += 1

    def leave(self) -> None:
        """TBD"""
        if self.enabled:
            self.level -= 1
            self.print("}")

    def flush(self) -> None:
        """TBD"""
        self.output.flush()

    def print(self, msg: str) -> None:
        """TBD"""
        if self.enabled:
            if self.level > 0:
                print(
                    f"{self.padding:>{self.level * self.indent}}{msg}", file=self.output
                )
            else:
                print(f"{msg}", file=self.output)


skillbase_util_trace: SkillbaseUtilTrace = SkillbaseUtilTrace(True, sys.stdout)


def skillbase_util_trace_get_enabled() -> bool:
    """TBD"""
    return skillbase_util_trace.get_enabled()


def skillbase_util_trace_set_enabled(enabled: bool) -> None:
    """TBD"""
    skillbase_util_trace.set_enabled(enabled)


def skillbase_util_trace_get_output() -> TextIO:
    """TBD"""
    return skillbase_util_trace.get_output()


def skillbase_util_trace_set_output(output: TextIO):
    """TBD"""
    skillbase_util_trace.set_output(output)


def skillbase_util_trace_func() -> Any:
    """TBD"""
    return inspect.stack()[1][3]


def skillbase_util_trace_print(msg: str) -> None:
    """TBD"""
    skillbase_util_trace.print(msg)


def skillbase_util_trace_enter(title: str) -> None:
    """TBD"""
    skillbase_util_trace.enter(title)


def skillbase_util_trace_leave() -> None:
    """TBD"""
    skillbase_util_trace.leave()


def skillbase_util_trace_dict(title: str, dictionary: dict) -> None:
    """TBD"""
    skillbase_util_trace_enter(title)
    for key, val in dictionary.items():
        skillbase_util_trace_print(f"{key} = {val}")
    skillbase_util_trace_leave()


# -------------------------------------------------------------
# Skillbase Util: Exec
# -------------------------------------------------------------


def skillbase_util_exec(cmd_line: str, cmd_data: Any, cmd_timeout: int) -> bytes:
    """Skillbase Util Exec"""
    return check_output(
        cmd_line, input=cmd_data, shell=True, timeout=cmd_timeout, stderr=STDOUT
    )


# -------------------------------------------------------------
# Skillbase Util: File I/O
# -------------------------------------------------------------


def skillbase_util_loadfile(inp_filepath: str) -> bytes:
    with open(inp_filepath, "rb") as inp_file:
        return inp_file.read()


def skillbase_util_savefile(out_filepath: str, out_filedata: bytes) -> int:
    with open(out_filepath, "wb") as out_file:
        return out_file.write(out_filedata)


def skillbase_util_loadtext(inp_filepath: str) -> AnyStr:
    with open(inp_filepath, "r", encoding="UTF-8") as inp_file:
        return inp_file.read()


def skillbase_util_savetext(out_filepath: str, out_filedata: AnyStr) -> int:
    with open(out_filepath, "w", encoding="UTF-8") as out_file:
        return out_file.write(out_filedata)


def skillbase_util_loadjson(inp_filepath: str, inp_varsdict: dict) -> AnyStr:
    inp_textbuff = skillbase_util_loadtext(inp_filepath)
    inp_template = skillbase_util_template(inp_textbuff, inp_varsdict)
    return json.loads(inp_template)


def skillbase_util_savejson(out_filepath: str, out_jsondata: AnyStr) -> None:
    skillbase_util_savetext(out_filepath, json.dumps(out_jsondata, indent=4))


# -------------------------------------------------------------
# Skillbase Util: Template
# -------------------------------------------------------------


def skillbase_util_template(inp_textbuff: AnyStr, inp_varsdict: dict) -> AnyStr:
    if inp_varsdict is not None:
        return Template(inp_textbuff).render(inp_varsdict)
    return inp_textbuff


# -------------------------------------------------------------
# Skillbase Util: Timer
# -------------------------------------------------------------


def skillbase_util_timer_enter() -> float:
    return time.time()


def skillbase_util_timer_leave(enter: float) -> float:
    return time.time() - enter


# -------------------------------------------------------------
# Skillbase Util: Misc
# -------------------------------------------------------------


def skillbase_util_parsebool(val: Any) -> bool:
    """TBD"""
    return eval(str(val)) if val is not None else False
