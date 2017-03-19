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

V mojej implementácií tohto problému je stav reprezentovaný triedou *Stav* a atribútmi. Najdôležitejší je dvojrozmerné pole prirodzených čísel *hlavolam [3][3]*, ktoré má rozmer 3x3, rovnako ako 8-hlavolam. Stav je tiež reprezentovaný *prioritou*, ktorú nastaví heurestika pri ohodnotení tohto stavu. Aby molo možné skonštruovať riešenie a postupnosť krokov, tak si pri generovaní nových stavov použitím operácií ukladám aj referenciu na rodičovský stav (*predchodca*) a použitú *operáciu*, ktorá zapríčinila vznik tohto nového stavu.

Stav ponúka aj sériu operácií(metód).
Operácia *nasledovníci()* vygeneruje pole maximálne 4 nasledujúcich stavov, ktoré môžu vzniknúť pri posunutí políčka dole, hore, doľava a doprava. Dôležitou súčasťou nasledovníkov je aj operácia *klunuj a vymen*, ktorá vygeneruje nový hlavolam a vymení políčka a tak simuluje pohyb políčkom. Z tohto sa generuje nový Stav. S nasledovníkmi sa spája aj operácia, ktorá vráti *súradnice zvoleného políčka(číslo)* ako Bod (Point). Táto operácia, je aj neskôr použitá heurestikou Manhattanskej vzdialenosti pri ohodnocovaní stavu. Operácia *unikatnyHash*, generuje unikátny reťazec znakov, ktorým je každý stav repreentovaný. Tento reťazec sa hashuje do hashovacej tabuľky a zabezzpečí, že nebudeme generovať duplicitné stavy a nedôjde k zacykleniu. Operácia na *výpis hlavolamu* iba vypíše pole *hlavolam* ako reťazec čísel a využíva sa pri vypisovaní riešenia.


### Algoritmus

Pri hľadaní riešenia je použitý podľa zadania *lačný algoritmus* alebo aj *hladný algoritmus*. 

Lačný algoritmus, je taký algoritmus, kde pri hľadaní riešenia sa v každom kroku vyberie uzol, ktorý je najbližšie k cieľu. Presnejšie, bude to uzol, ktorý reprezentuje stav, ktorý je podľa odhadu najbližší k cieľovému stavu. Hovoríme opäť o odhade, pretože najčastejšie sa cena dosiahnutia cieľa z daného stavu nedá určiť presne [1]. 

Algoritmus je v mojej implementácií reprezentovaný *pociatočným stavom*, *cieľovým stavom*, *zvolenou heuresikoou*, *prioritným radom*, *hashovacou tabuľkou*, *počtom prehľadaných stavov* a *zásobníkom na riešenie*.

Algoritmus vezme 

### Heurestiky 

## Porovnanie heurestík 1. a 2. 

## Zhodnotenie riešenia



