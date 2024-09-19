"""Skillbase Config"""

from .skillbase_util import (
    skillbase_util_loadjson,
    skillbase_util_trace_enter,
    skillbase_util_trace_leave,
    skillbase_util_trace_print,
)


class SkillbaseConfig:
    """Skillbase Config"""

    def __init__(self, json):
        self.root = json.get("root")

    @classmethod
    def load(cls, filepath):
        return SkillbaseConfig(skillbase_util_loadjson(filepath, None))

    def trace(self) -> None:
        skillbase_util_trace_enter("config")
        skillbase_util_trace_print("root = {}", self.root)
        skillbase_util_trace_leave()
