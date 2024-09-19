"""
Skillbase Server
"""

import sys
from pathlib import Path
from typing import Any

from easydict import EasyDict
from flask import Flask
from .skillbase_context import SkillbaseContext
from .skillbase_defs import (
    SKILLBASE_HTTPHOST,
    SKILLBASE_HTTPPORT,
    SKILLBASE_VERSION,
)

_app: Flask = Flask(__name__)

Server: EasyDict = EasyDict(
    {
        "context": None,
        "http_host": SKILLBASE_HTTPHOST,
        "http_port": SKILLBASE_HTTPPORT,
        "root_path": None,
        "temp_path": None,
    }
)


def skillbase_server_main(context: SkillbaseContext) -> int:
    """Skillbase Server: Main"""

    try:
        context.tracer_enter(context.tracer_func())

        # Extract the command arguments using default values
        args: EasyDict = EasyDict(
            {
                "http_host": context.getarg("http_host", SKILLBASE_HTTPHOST),
                "http_port": context.getarg("http_port", SKILLBASE_HTTPPORT),
            }
        )

        context.tracer_enter("args")
        context.tracer_print(f"http_host = {args.http_host}")
        context.tracer_print(f"http_port = {args.http_port}")
        context.tracer_leave()

        # Save the configuration
        Server.context = context
        Server.http_host = args.http_host
        Server.http_port = int(args.http_port, 0)
        Server.root_path = Path(context.config.rootdir).absolute()
        Server.temp_path = Path(Server.root_path, "temp")
        context.tracer_print_dict("Server", Server)

        # Make sure the subdirectories exist
        Server.temp_path.mkdir(exist_ok=True, parents=True)

        # Run the web server
        _app.run(host=Server.http_host, port=Server.http_port)

        return 0

    finally:
        context.tracer_leave()


@_app.route("/project", methods=["POST"])
def _skillbase_server_project() -> Any:
    """Skillbase Server: project"""

    try:
        Server.context.tracer_enter(Server.context.tracer_func())
        return "TBD"

    except Exception:
        Server.context.tracer_print(sys.exc_info()[1])

    finally:
        Server.context.tracer_leave()


@_app.route("/version", methods=["GET"])
def _skillbase_server_version() -> Any:
    """Skillbase Server: Version"""

    try:
        Server.context.tracer_enter(Server.context.tracer_func())

        return f"""
            <p>Skillbase version: {SKILLBASE_VERSION}
            <p>Python version:    {sys.version}
            <p>Flask version:     {Flask.__version__}
        """

    except Exception:
        Server.context.tracer_print(sys.exc_info()[1])

    finally:
        Server.context.tracer_leave()
