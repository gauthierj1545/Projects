#! /usr/bin/python3
import cgi
import cgitb
form = cgi.FieldStorage()

print("<html>")
print("<body>")
print()
print("<form action=\"list_of_games\" method=\"get\">")

cursor.execute("SELECT playerOne, P1Color, playerTwo, P2Color FROM games;")
rows = cursor.fetchall()

print("<table>")
print("<tr><td>TableID </td><td>ProductID </td><td>CustomerID </td></tr>")
for row in rows:
    print("<tr><td>"+str(row[0])+"</td><td>"+str(row[1])+"</td><td>"+str(row[2])+str(row[3])+"</td></tr>" )
print( "<input type=textarea name=chosen_game value=\"select a game\">")
print("</table>")