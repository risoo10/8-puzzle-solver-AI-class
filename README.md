# 2. Zadanie 
### Umelá inteligencia (predmet)
#### Richard Mocák

Slovenská technická univerzita v Bratislave,

Fakulta informatiky a informačných technológií


## Zadanie - d)

#### Problém č. 2
Našou úlohou je nájsť riešenie 8-hlavolamu. Hlavolam je zložený z 8 očíslovaných políčok a jedného prázdneho miesta. Políčka je možné presúvať hore, dole, vľavo alebo vpravo, ale len ak je tým smerom medzera. Je vždy daná nejaká východisková a nejaká cieľová pozícia a je potrebné nájsť postupnosť krokov, ktoré vedú z jednej pozície do druhej.

#### Špecifikácia vyhľadávacieho algoritmu
Použite algoritmus lačného hľadania, porovnajte výsledky heuristík 1. a 2.

Heurestika 1: Počet políčok, ktoré nie sú na svojom mieste.

Heurestika 2: Súčet vzdialeností jednotlivých políčok od ich cieľovej pozície (Manhattanská vzdialenosť).



## Riešenie

### Uzol (Stav)
*Stav* alebo *uzol* predstavuje unikátny opis stavu hlavolamu, do ktorého sa dospelo postupnosťou operácií. Aby mohol lačný algoritmus s heurestikou nájsť riešenie, tak musí byť každý stav správne a unikátne reprezentovaný. 

V mojej implementácií tohto problému je stav reprezentovaný triedou *Stav* a viacerými atribútmi. Najdôležitejší je dvojrozmerné pole prirodzených čísel *hlavolam [3][3]*, ktoré má rozmer 3x3, rovnako ako 8-hlavolam. Stav je tiež reprezentovaný *prioritou*, ktorú nastaví heurestika pri ohodnotení tohto stavu. Aby molo možné skonštruovať riešenie a postupnosť krokov, tak si pri generovaní nových stavov použitím operácií ukladám aj referenciu na rodičovský stav (*predchodca*) a použitú *operáciu*, ktorá zapríčinila vznik tohto nového stavu.

Stav ponúka aj sériu operácií(metód).
Operácia *nasledovníci()* vygeneruje pole maximálne 4 nasledujúcich stavov, ktoré môžu vzniknúť pri posunutí políčka dole, hore, doľava a doprava. Dôležitou súčasťou nasledovníkov je aj operácia *klunuj a vymen*, ktorá vygeneruje nový hlavolam a vymení políčka a tak simuluje pohyb políčkom. Z tohto sa generuje nový Stav. S nasledovníkmi sa spája aj operácia, ktorá vráti *súradnice zvoleného políčka(číslo)* ako Bod (Point). Táto operácia, je aj neskôr použitá heurestikou Manhattanskej vzdialenosti pri ohodnocovaní stavu. Operácia *unikatnyHash*, generuje unikátny reťazec znakov, ktorým je každý stav repreentovaný. Tento reťazec sa hashuje do hashovacej tabuľky a zabezzpečí, že nebudeme generovať duplicitné stavy a nedôjde k zacykleniu. Operácia na *výpis hlavolamu* iba vypíše pole *hlavolam* ako reťazec čísel a využíva sa pri vypisovaní riešenia.


### Algoritmus

Pri hľadaní riešenia je použitý podľa zadania *lačný algoritmus* alebo aj *hladný algoritmus*. 

Lačný algoritmus, je taký algoritmus, kde pri hľadaní riešenia sa v každom kroku vyberie uzol, ktorý je najbližšie k cieľu. Presnejšie, bude to uzol, ktorý reprezentuje stav, ktorý je podľa odhadu najbližší k cieľovému stavu. Hovoríme opäť o odhade, pretože najčastejšie sa cena dosiahnutia cieľa z daného stavu nedá určiť presne [1]. 

Algoritmus je v mojej implementácií reprezentovaný *pociatočným stavom*, *cieľovým stavom*, *zvolenou heurestikou*, *prioritným radom*, *hashovacou tabuľkou*, *počtom prehľadaných stavov* a *zásobníkom na riešenie*.

Algoritmus vezme počiatočný stav a pridá ho do fronty s prislúchajucou prioritou podľa heurestickej funkcie. Spustí sa cyklus, ktorý sa vykonáva kým nie je fronta so stavmi  prázdna alebo sa dopracovalo k cieľovému stavu. V cykle sa vždy vyberie najmenší prvok z fronty (to je stav, ktorý je podľa odhadu heurestickej funkcie najbližšie k cieľovému stavu). Po vybratí tohto stavu sa inkrementuje premenná *počet prehľadaných stavov* pre štatistickú informáciu pre neskoršie porovnávanie heurestík. Ak tento stav nie je cieľový (heurestika ho neohodnotila nulou), tak sa vegenerujú nasledovníci(operácia je implementovaná v triede Stav a popísana v časti dokumentácie o Stave) tohto stavu. Každý z týcho nasledovníkov je overený na duplicitu prostredníctvom Hashovacej tabuľky, ktorá rýchlo rozhodne či takýto stav už bol niekedy zahashovaný podľa *unikátneho hashu* (viac v časti o Stave). Ak sa jedná o nepreskúmaný stav, tak je ohodnotení heurestickou funkciou a hodnotenie sa zapíše do atribútu Stav.*priorita* a vložený do prioritného radu (fronty), kde prebehne prípadne preusporiadanie prkov. Toto sa opakuje s každým cyklom až kým sa cyklus neukončí jedným zo spomínaných spôsobov. Ak bol akýsi Stav ohodnotený nulovou prioritou, čiže cieľový stav, tak je uložený do premennej result, kde pozlúži na rekurzívne generovanie riešenia. 

Algoritmus disponuje ešte jednou operáciou (metódou). *Postupnost krokov*, ktorá vygeneruje postupnosť krokov (operácií) vďaka atribútu *predchodca* v stave. Tieto riešenia sú uchované v správnom poradí v *zásobníku výsledok*. Toto je výstupom algoritmu a riešením ak riešenie existuje.  
 

### Heurestiky 
Heurestická funkcia  je funkcia, ktorá vypočíta odhad ceny cesty z daného uzla do cieľového uzla [1].

V implementácií som heurestiky aplikoval ako triedy, ktoré implementovali rozhranie *Heurestika* a jej metódu *vyhodnotStav(Stav s)*. Keďže každá 

#### Heurestika 1.
Táto heurestická funkcia vyhodnocuje stav hlavolamu podľa  počtu políčok, ktoré nie sú na rovnakej pozícii ako v cieľovom stave. 
 
 - obrazok
 
 [0 1 2]    [3 2 1]
 [3 4 5]    [6 4 5]
 [6 7 8]    [0 7 8]
 
 
 0 + 0 + 0 + 0 + 1 + 1 + 0 + 1 + 1 = 4
 
 Tento aktuálny stav by táto heurestická funckia ohodnotila čislom 4.  
 
 V mojej implementácií táto funckia iteruje dvojrozmerným poľom a na každej pozícií overí hodnotu políčka na cieľovom hlavolame. Ak sa hodnoty nezhodujú tak inkrementuje výsledok. Po ierácií vráti výsledok do algoritmu.
 
 #### Heurestika 2. (tzv. Manhattanská vzdialenosť)
 Táto funkcia hodnotí stav sumou Manhattanských vzdialeností pre každé políčko v stave do finálnej pozície v cieľovom stave. Manhattanská vzdialenosť je taká vzdialenosť, že predstavujé počet prejdených vodorovných a zvislých hrán v štvorcovej sieti z bodu A do bodu B. 
 
 - obrázok 
 
 Manhattanská vzdialenosť je rovná 1 + 1 + 1 + 1 = 4.
 
 - obrazok heurestika
 
 [1   2   3   4   5   6   7   8]
  1 + 1 + 1 + 0 + 0 + 1 + 0 + 0 = 4
  
 Súčet Manhattanských vzdialeností pre každé políčko v stave je rovný 4.
 
 V implementácií je táto funkcia implmenetovaná pomocou jedného cyklu od 1 až po 8 vrátane, a pre každé číslo (políčko) sa nájdu súradnice x a y pre aktuálny a súradnice pre cieľový stav. Urobí rozdiel x-ových súradníc v absolútnej hodnote a pripočíta absolútny rozdiel y-ových súradnic. Toto sa pre každé políčko sumuje v jednej premennej *suma*. Výsledok je ohodnotenie stavu a odoslané ako výsledok do algoritmu.
 

## Porovnanie výsledkov heurestík 1. a 2. 

Pre porovnanie, ktorá heurestická funkcia vie priemerne lepšie odhadnúť stav ako najlepší využijeme testovanie na 4 scénároch. V každom scenári bude spoločný vstupný stav a cieľový stav a bude sa sledovať výsledný počet krokov a počet prehľadaných stavov, ktoré musel algoritmus spracovať aby našiel riešenie. 

1. Scenár 
- počiatok :  [ 0 1 2 ][ 3 4 5 ][ 6 7 8 ]          
- cieľ :      [ 0 1 2 ][ 3 5 4 ][ 6 8 7 ]
- heurestika 1: 32 krokov, 242 stavov
- heurestika 2: 20 krokov, 437 stavov


2. Scenár 
- počiatok :  [ 1 2 3 ][ 4 5 6 ][ 7 8 0 ]          
- cieľ :      [ 8 6 7 ][ 2 5 4 ][ 3 0 1 ]
- heurestika 1: 97 krokov, 420 stavov
- heurestika 2: 43 krokov, 74 stavov   
    

3. Scenár 
- počiatok :  [ 5 6 0 ][ 1 3 2 ][ 4 8 7 ]          
- cieľ :      [ 0 6 5 ][ 4 3 8 ][ 2 1 7 ]
- heurestika 1: 38 krokov, 250 stavov
- heurestika 2: 94 krokov, 422 stavov


4. Scenár 
- počiatok :  [ 3 4 7 ][ 0 8 6 ][ 5 2 1 ]          
- cieľ :      [ 1 0 8 ][ 7 5 4 ][ 6 2 3 ]
- heurestika 1: 114 krokov, 972 stavov
- heurestika 2: 54 krokov, 320 stavov
    
    
5. Scenár 
- počiatok :  [ 2 7 6 ][ 0 5 4 ][ 8 3 1 ]          
- cieľ :      [ 6 0 8 ][ 5 1 3 ][ 2 4 7 ]
- heurestika 1: 54 krokov, 331 stavov
- heurestika 2: 38 krokov, 57 stavov


## Zhodnotenie riešenia
Ak riešenie neexistuje tak moja implementácia prehľadá všetky stavy a algoritmus skončí bez správneho riešenia. Ak však nájde správne riešenie tak do okna vypíše postup operácií a návod ako zo žiadaného stavu dospieť k cieľovému. Riešenie je úplne ale poskytuje len riešenie pre hlavolam rozmeru 3x3. Tento systém by sa dal rozšíriť pre systém m x n upravením natvrdo vpísaných dát a menšou modifikáciou. Práca na zadaní bola zaujimavá a som spokojný s výsledným spracovaním a formátom môjho riešenia.


### Použitá literatúra
[1] NÁVRAT, P. a kol.: Umelá inteligencia. Vydavateľstvo STU Bratislava, 2007. ISBN 788-022-730-80-8.



