TesiAndreaSelva
===============

Progetto di Tesi. Andrea Selva matricola 714002 Uninversità Insubria di Varese.
andrea.selva90@gmail.com

Step 1)

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

