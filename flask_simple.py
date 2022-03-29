from flask import Flask, request, render_template, url_for, redirect, make_response
import MySQLdb

app = Flask(__name__)


@app.route("/")
def index():
    user = check_for_cookie()
    if user is None:
        return render_template("log_in_page")
    return render_template("list_of_games")
    return render_template("game")


@app.route("/game")
def game_handle():
    cursor = sql_connect()
    cursor.execute("SELECT color, Xcoor, yCoor FROM games")


@app.route("/game_list")
def game_list_handle():
    cursor = sql_connect()
    cursor.execute("SELECT playerOne, P1Color, player2, P2Color FROM games WHERE playerOne=\"%s\")",
                   request.values["sessionID"])


@app.route("/login")
def loginhandle():
    if "username" in request.values:
        ID_as_int = random.randint(0, 16 ** 64)
        ID_as_str = "%064x" % ID_as_int

        cursor = sql_connect()
        cursor.execut("INSERT INTO sessions(sessionID, userName) VALUES(\"%s\",\"%s\")",
                      (ID_as_str, request.values["username"])
        conn.commit()

        resp = redirect(url_for("idex"))
        resp.set_cookie("SessionID", ID_as_str)

    return resp


def sql_connect():
    if not hasattr(request, "conn"):
        request.conn = MySQLdb.connect(host=passwords.SQL_HOST, user=passwords.SQL_USER, password=passwords.SQL_PASSWD,
                                       db="proj7")
    return conn.cursor()


def check_for_cookie():
    cursor = sql_connect()

    if "sessionID" not in request.cookies:
        return None

    cursor.execute("SELECT usename FROM sessions WHERE time > NOW() AND sessionID=" + request.cookies["sessionID"])
    session_info = cursor.fetchall()
    if len(session_info) > 0:
        return session[0][0]

    return None