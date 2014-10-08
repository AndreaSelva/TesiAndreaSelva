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

**Risultati**

Misurazioni in millisecondi:

Grid Home Best Practices:

-20 Views

-4,242 ms Measure

-2,106 ms Layout

-2.808 ms Draw

TOT 9.156 ms



Best Practices Hierarchy Layout

-27 Views

-6.989 ms Measure

-1.434 ms Layout

-4.151 ms Draw

TOT 12.574 ms



Not Best Practices Hierarchy Layout

-36 Views

-6.531 ms Measure

-2.075 ms Layout

-4.517 ms Draw

TOT 13.123 ms



Si è ottenuto un poco significativo miglioramento pari al 5% usando la Best Practices.

