import os
from termcolor import colored
out = open("salida.txt",'w', encoding='utf-8')
HEADER = '''
INSERT INTO `gpus` (`modelname`, `company`, `year`, `memory`, `power`, `socket`, `price`, `type`, `imageurl`) 
VALUES '''
out.write(HEADER)

with open('src/main/webapp/resources/htmlSource.html', 'r', encoding='utf-8') as htmlfile:    
    #Atributos
    name = ""
    image = "https://wpdirecto.com/wp-content/uploads/2017/08/solucionar-error-wordpress.jpg"
    price = "0"
    company = "not scrapped"
    year = "2000"
    memory = "6000"
    power = "2000"
    socket="socket"
    type="Standard"    

#Palabra Roo Premonicion

    for line in htmlfile:
        if("<article data-name=" in line):
            name = line.split('" data-id')[0].replace('<article data-name="','')
            #print(name)
            
        if('class="c-product-card__image cy-product-image"' in line):
            image = str(line.split(' src="')[1].split('" alt="')[0])
           # print(colored("\t" + image,'cyan'))
            
        if('€</span>' in line):
            price = line.replace('<span>','').replace('</span>','').strip()
            #print("\t" + price)
            
        if('AMD' in name):
            company = 'AMD'
        if('NVIDIA' in name):
            company = 'NVIDIA'
        

        

        if('<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">' in line): 
            print(colored('Cycle','green'))
        if('<div class="col-xs-6 col-sm-4 col-md-4 col-lg-4">' in line):
            SQL_INSERT = f"('{name}', '{company}', '{year}', '{memory}', '{power}', '{socket}', '{price}', '{type}', '{image}'),\n"
            out.write(SQL_INSERT)

out.close()
        