"""Skillbase Context"""

import argparse
import inspect
from pathlib import Path
from typing import Any

from easydict import EasyDict
from .skillbase_config import SkillbaseConfig
from .skillbase_util import (
    skillbase_util_trace_enter,
    skillbase_util_trace_leave,
    skillbase_util_trace_print,
)


class SkillbaseContext:
    """Skillbase Context"""

    def __init__(self, conf: SkillbaseConfig, args: dict):
        self.conf = conf
        self.root = Path(conf.root).absolute()
        self.temp = Path(self.root, "temp")
        self.args = EasyDict(vars(args))
        self.cmnd = args.cmnd
        self.save = None

    def push(self, args: EasyDict) -> None:
        self.save = self.args
        self.args = args

    def pop(self) -> None:
        self.args = self.save

    def getarg(self, attr: str, dflt: any) -> Any:
        if isinstance(self.args, EasyDict):
            return self.args.get(attr) if self.args.get(attr) is not None else dflt
        if isinstance(self.args, argparse.Namespace):
            arg_vars: vars = vars(self.args)
            return arg_vars[attr] if arg_vars[attr] is not None else dflt
        return self.args.get(attr) if self.args.get(attr) is not None else dflt

    def tracer_enter(self, title: str) -> None:
        skillbase_util_trace_enter(title)

    def tracer_func(self) -> str:
        return inspect.stack()[1][3]

    def tracer_leave(self) -> None:
        skillbase_util_trace_leave()

    def tracer_print(self, msg: str) -> None:
        skillbase_util_trace_print(msg)

    def tracer_print_optional(self, val: Any, msg: str) -> None:
        if val is not None:
            skillbase_util_trace_print(msg)

    def trace(self) -> None:
        skillbase_util_trace_enter("context")
        self.tracer_print(f"conf = {self.conf}")
        self.tracer_print(f"cmnd = {self.cmnd}")
        self.tracer_print(f"root = {self.root}")
        self.tracer_print(f"temp = {self.temp}")
        skillbase_util_trace_leave()
