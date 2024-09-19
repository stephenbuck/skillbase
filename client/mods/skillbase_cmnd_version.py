"""Skillbase Cmnd Version"""

import sys

from .skillbase_context import SkillbaseContext
from .skillbase_defs import SKILLBASE_VERSION


def skillbase_cmnd_version(context: SkillbaseContext) -> int:
    """TBD"""
    context.tracer_print(f"Skillbase version: {SKILLBASE_VERSION}")
    context.tracer_print(f"Python version:    {sys.version}")
    return 0
