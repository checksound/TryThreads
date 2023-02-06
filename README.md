# Try Threads

## Creazione di thread

* Esempio di creazione di thread come sottoclasse della classe `java.lang.Thread`: [trythreads.simple.MyThread](./src/main/java/trythreads/simple/MyThread.java). Script di esecuzione dell'applicazione `runMyThread.bat`;
* Esempio di crezione di thread tramite implemenentazione dell'interfaccia `java.lang.Runnable`: 
[trythreads.simple.MyThreadRunnable](./src/main/java/trythreads/simple/MyThreadRunnable.java). Script di esecuzione dell'applicazione `runMyThreadRunnable.bat`;

## Utilizzo del metodo Thread.join

Il metodo [Thread.join()](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Thread.html#join) aspetta che il thread, su cui è invocato il metodo, abbia finito.

La classe [trythreads.simple.MyThreadWithoutJoin](./src/main/java/trythreads/simple/MyThreadWithoutJoin.java) esecuzione di un thread che esegue l'incremento di un contatore, senza l'utilizzo del metodo `join` per prendere il risultato alla fine dell'esecuzione del thread; Script di esecuzione dell'applicazione `runMyThreadWithouJoin.bat`.

La classe [trythreads.simple.MyThreadWithJoin](./src/main/java/trythreads/simple/MyThreadWithJoin.java) esecuzione di un thread che esegue l'incremento di un contatore, mentre il main invocando il metodo `join` del thread aspetta il termine dell'esecuzione del thread; Script di esecuzione dell'applicazione `runMyThreadWithJoin.bat`.

La classe [trythreads.simple.MyThreadWithJoinWithTime](./src/main/java/trythreads/simple/MyThreadWithJoinWithTime.java) esecuzione di un thread che esegue l'incremento di un contatore, mentre il main invocando il metodo `join(long timemillis)` del thread aspetta il termine dell'esecuzione del thread o che scada il timeout; Script di esecuzione dell'applicazione `runMyThreadWithJoinWithTime.bat`.

## Interruzione dei thread

Il metodo `interrupt` può essere utilizzato per richiedere il termine di un thread.

Esempio di utilizzo delle primitive dei thread: `join()`, `sleep()`, `isAlive()`, `interrupt()`: [trythreads.simple.SimpleThreads](./src/main/java/trythreads/simple/SimpleThreads.java); script di esecuzione dell'applicazione `runSimpleThreads.bat [<NUM_SECONDI>]`. Ad esempio `runSimpleThreads.bat 12` per far terminare l'applicazione dopo 12 secondi o `runSimpleThreads.bat` per far terminare l'applicazione quando a finito di visualizzare in output i messaggi.

Vedi differenza per il semplice utilizzo di `Thread.sleeep()`: [trythreads.simple.SleepMessages](./src/main/java/trythreads/simple/SleepMessages.java); script di lancio dell'applicazione è `runSleepMessages.bat`.

## Animazione

Innanzitutto, tutti i componenenti grafici delle Swing devono essere configurati dal thread che gestisce l'_event dispatch_, il thread di controllo che passa gli eventi come il click del mouse e il digitare dei tasti ai componenti della user interface.

Il seguente frammento di codice è utilizzato per eseguire statement nel thread di _event dispatch_:

```java
EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               // Statement
            }
         });
```


Nel nostro esempio:

```java
EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               JFrame frame = new BounceFrame();
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
````

Esempio della palla che rimbalza [bounce.v1.Bounce](./src/main/java/bounce/v1/Bounce.java); lo script di esecuzione dell'applicazione è `./runBounce.bat`.

Se si manda in esecuzione il programma, la palla si muove, ma prende il controllo di tutta l'applicazione. Se si volesse fermare l'applicazione prima che il moto della palla sia finito e si clicca sul pulsate _Close_, la palla continua a muoversi comunque. Non si può interagire con il programma finché la palla non ha terminato di muoversi.

Rendiamo l'esempio della palla che rimbalza più responsive eseguendo il codice che muove la palla in un thread separato. Infatti, così siamo in grado di eseguire il lancio di più palle, ognuna mossa dal proprio thread. In aggiunta, il thread di _dispatch degli eventi_ continuerà l'esecuzione in parallelo, occupandosi di gestire gli eventi della user interface. In questo modo dato che  ogni thread viene eseguito 'contemporaneamente', anche il thread che gestisce l'_event dipatch_, ha l'opportunità di accorgersi che l'utente clicca sul bottone _Close_ mentre le palle stanno rimbalzando. 

Esempio: [bounce.v2.Bounce](./src/main/java/bounce/v2/Bounce.java). Lo script di esecuzione dell'applicazione è `runBounceThread.bat`.

### Utilizzo di interrupt

Vedi esempio [bounce.v3.Bounce](./src/main/java/bounce/v3/Bounce.java) con bottone di *Stop* per terminare i thread in esecuzione. Lo script di esecuzione dell'applicazione è `runBounceThreadWithStop.bat`.

