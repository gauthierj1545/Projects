#! /usr/bin/python3
import cgi
import cgitb

form = cgi.FieldStorage()

print("<html>")
print("<body>")
print()
print("<form action=\"log_in_page\" method=\"post\">")
print("<input type= login name=usernsame value=\"Please login\">")
print("</form")
print("</body>")
print("</html>")