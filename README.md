TesiAndreaSelva
===============

Progetto di Tesi. Andrea Selva matricola 714002 Uninversità Insubria di Varese.
andrea.selva90@gmail.com

**Step 1)**

-Creazione di una activity principale composta da GridView in cui saranno presenti
due colonne: da una parte le Best Practices (BP) dall'altra le Not Best Practices (NBP). 
Cliccando su un oggetto della griglia si accede all'activity relativa a quella BP o NBP

-Creazione di: Best Practices Lauout Hierarchy nella quale si constata 
l'effettivo miglioramento che si ottiene utilizzando una gerarchia corta nell'albero 
delle viste piuttosto che a un inserimento di LinearLayout annidato uno dentro l'altro.

-Effettuare 10 annidamenti e verificare/confrontare i risultati ottenuti.

**RISULTATI**

Table | **Home** | **BPHL** | **NBPHL**
:---: | :---: | :---: | :---: 
**Measure** | 4.242 ms | 6.989 ms | 6.531 ms 
**Layout** | 2.106 ms | 1.434 ms | 2.075 ms
**Draw** | 2.808 ms | 4.151 ms | 4.517 ms
*TOT* | *9.153 ms* | *12.574 ms* | *13.123 ms*

Si è ottenuto un poco significativo miglioramento pari al 5% usando la Best Practices.

**Step 2)**

-Modificare le Activity in modo da creare dinamicamente le view così da riuscire a 
vedere le differenze con numerosi oggetti.

-Cercare informazioni su: significato colori e measure, layout, draw in Hierarchy View

**RISULTATI**

Table | **Home** | **BPHL** | **NBPHL**
:---: | :---: | :---: | :---: 
**Measure** | 4.522 ms | 22.219 ms | 22.738 ms 
**Layout** | 2.108 ms | 2.689 ms | 10.102 ms
**Draw** | 2.992 ms | 9.492 ms | 9.126 ms
*TOT* | *9.622 ms* | *34.400 ms* | *41.966 ms*

Si è ottenuto un miglioramento prestazionale pari al 18% usando la Best Practices
un netto miglioramento si ottiene considerando che nella not best practices è
possibile annidare 44 viste e risulta quindi il numero massimo di oggetti creabili
in altro caso verrà sollevata un eccezione stackovefflowerror. Nella BP invece è 
possibile creare infiniti oggetti. Il tempo di creazioni di quest'ultimi è proporzionale.


The colors indicate the following relative performance:
Green: For this part of the render time, this View is in the faster 
50% of all the View objects in the tree. For example, a green dot 
for the measure time means that this View has a faster measure time 
than 50% of the View objects in the tree.
Yellow: For this part of the render time, this View is in the 
slower 50% of all the View objects in the tree. For example, a 
yellow dot for the layout time means that this View has a slower 
layout time than 50% of the View objects in the tree.
Red: For this part of the render time, this View is the slowest one 
in the tree. For example, a red dot for the draw time means that 
this View takes the most time to draw of all the View objects in 
the tree.

There are basically 3 steps during the render process. First, your 
view gets measured, this is the just the width and height. Then the 
controls are go through layout, this basically just tells all the 
views where they are located inside the measurements. And finally, 
everything is drawn. This is where the controls are actually drawn 
on the canvas.
