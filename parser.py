import json
import re
from urllib.request import urlopen
from bs4 import BeautifulSoup
from lxml import html
import requests

urls=["http://paizo.com/pathfinderRPG/prd/bestiary/monsterIndex.html", 
        "http://paizo.com/pathfinderRPG/prd/bestiary2/additionalMonsterIndex.html", 
        "http://paizo.com/pathfinderRPG/prd/bestiary3/monsterIndex.html",
        "http://paizo.com/pathfinderRPG/prd/bestiary4/monsterIndex.html",
        "http://paizo.com/pathfinderRPG/prd/bestiary5/index.html"]
number=0
error=0
for urlparse in urls:
    print(urlparse)
    page = urlopen(urlparse) # Insert your URL to extract
    bsObj = BeautifulSoup(page.read(),"lxml")
    if(urlparse=="http://paizo.com/pathfinderRPG/prd/bestiary/monsterIndex.html"):
        url="http://paizo.com/pathfinderRPG/prd/bestiary/"
    else:
        url="http://paizo.com"
    namePrevious=list()
    index=0
    data = bsObj.findAll('div',attrs={'class':'index'})
    for div in data:
        links = div.findAll('a')
        for link in links:
            #on verifie que la balise a contient un lien 
            if(link.get('href')):
                #on verifie que le lien soit un monstre
                #if(re.search('/pathfinderRPG/prd/bestiary.*',link.get('href'))):
                newPage=requests.get(url+link.get('href'))       
                tree = html.fromstring(newPage.content)
                name=tree.xpath("//*[contains(@class, 'monster-header')]/text()")
                if(not name):
                    name=tree.xpath("//h1/text()")
                if(name==namePrevious):
                    index+=1
                else:
                    index=0
                try:
                    print("#########################")
                    print(name[index])
                    print("#########################")
                    if(len(name)>=1):
                        if(index==len(name)-1):
                            excerpts = re.findall(r'('+name[index]+')(.*?)('+'</div>'+')', newPage.text, re.DOTALL)
                        else:
                            excerpts = re.findall(r'('+name[index]+')(.*?)('+name[index+1]+'.*?</h1>)', newPage.text, re.DOTALL)
                        for e in excerpts:
                            soup2 = BeautifulSoup(''.join(e),"lxml")
                            for link2 in soup2.findAll('a'):
                                if(str(link2).find('/spells/')==-1):
                                    continue
                                    
                                else :
                                    print(link2.next)
                except:
                    error+=1
                namePrevious=name
                number+=1
print(number)
print(error)
