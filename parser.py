import json
from lxml import html
import requests

url="http://www.pathfinder-fr.org/Wiki/Pathfinder-RPG.Monstres.ashx"
page = requests.get(url)
tree=html.fromstring(page.content)
allMonsters1=tree.xpath("/html/body/form/table/tbody/tr[1]/td[2]/div[2]/div/div[2]/div/div[4]/table[2]/tbody/tr/td[1]/ul[1]")
print(allMonsters1)
