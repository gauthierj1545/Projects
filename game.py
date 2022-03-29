#! /usr/bin/python3
import cgi
import cgitb
form = cgi.FieldStorage()

print("<html>")
print("<body>")
print()
print("<form action=\"list_of_games\" method=\"get\">")

cursor.execute("SELECT color, Xcoordinate, Ycoordinate FROM moves where game=" + FieldStorage["sessionID"])
rows = cursor.fetchall()

print("<table>")
print("<tr><td>Color </td><tdX-coor </td><td>Y-coor </td></tr>")
for row in rows:
        print("<tr><td>"+str(row[0])+"</td><td>"+str(row[1])+"</td><td>"+str(row[2])+"</tr>" )
print( "<input type=textarea name=chosen_game value=\"enter coordinates\">")
print("</table>")