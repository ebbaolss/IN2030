#Oppgave5 - syntaksfeil, bruk jernbanediagram i kompendium

#a) linje 6 - ** er ingen operator i asp

a = 4
b = 2 ** a
print("Svaret er", b)


#b) linje 15, 16 og 17 - "\"

a = "x"
a_ = a + "-"
_a = "-" + a
print("\"a\"=", a)
print("\"a_\"=", a_)
print("\"_a\"=", _a)


#c) linje 24 - kan ikke ha at x = y+1 etter en if

x = 9
y = -1
if x = y+1: x = x+1


#d) linje 36 - her må det indenteres etter else:

v = input("Skriv et tall: ")
if v < 0: 
    print(v, "er negativt." ) 
else: 
if v==0: 
    print(v, "er null." )
else: 
print(v, "er positivt." ) 


#e) linje 41 - det finnes ikke tall 001 og 010, disse må skrives som en string.

tab=[ 001, 010, 100 ] 
if tab[0] <=tab[1] <=tab[2]: 
    print("Sortert") 
else: 
    print("Ikke sortert") 


#f) ingen feil

n=0 

def f (v): 
    global n 
    n=n + v 
    
f(1); f(2); f(3) 
print("Svaret", 'er' , n) 


#g) 

for i in range(0,10): j=i+1; 
    print (j*j);