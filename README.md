# Try Threads

## Creazione di thread

* Esempio di creazione di thread come sottoclasse della classe `java.lang.Thread`: [trythreads.simple.MyThread](./src/main/java/trythreads/simple/MyThread.java). Script di esecuzione dell'applicazione `runMyThread.bat`;
* Esempio di crezione di thread tramite implemenentazione dell'interfaccia `java.lang.Runnable`: 
[trythreads.simple.MyThreadRunnable](./src/main/java/trythreads/simple/MyThreadRunnable.java). Script di esecuzione dell'applicazione `runMyThreadRunnable.bat`;

## Utilizzo del metodo Thread.join

Il metodo [Thread.join()](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Thread.html#join) aspetta che il thread, su cui è invocato il metodo, abbia finito.

La classe [trythreads.simple.MyThreadWithoutJoin](./src/main/java/trythreads/simple/MyThreadWithoutJoin.java) esecuzione di un thread che esegue l'incremento di un contatore, senza l'utilizzo del metodo `join` per prendere il risultato alla fine dell'esecuzione del thread; Script di esecuzione dell'applicazione `runMyThreadWithouJoin.bat`.

La classe [trythreads.simple.MyThreadWithJoin](./src/main/java/trythreads/simple/MyThreadWithJoin.java) esecuzione di un thread che esegue l'incremento di un contatore, mentre il main invocando il metodo `join` del thread aspetta il termine dell'esecuzione del thread; Script di esecuzione dell'applicazione `runMyThreadWithJoin.bat`.

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

Esempio della palla che rimbalza [bounce.Bounce](./src/main/java/bounce/Bounce.java); lo script di esecuzione dell'applicazione è `./runBounce.bat`.

Se si manda in esecuzione il programma, la palla si muove, ma prende il controllo di tutta l'applicazione. Se si volesse fermare l'applicazione prima che il moto della palla sia finito e si clicca sul pulsate _Close_, la palla continua a muoversi comunque. Non si può interagire con il programma finché la palla non ha terminato di muoversi.

Rendiamo l'esempio della palla che rimbalza più responsive eseguendo il codice che muove la palla in un thread separato. Infatti, così siamo in grado di eseguire il lancio di più palle, ognuna mossa dal proprio thread. In aggiunta, il thread di _dispatch degli eventi_ continuerà l'esecuzione in parallelo, occupandosi di gestire gli eventi della user interface. In questo modo dato che  ogni thread viene eseguito 'contemporaneamente', anche il thread che gestisce l'_event dipatch_, ha l'opportunità di accorgersi che l'utente clicca sul bottone _Close_ mentre le palle stanno rimbalzando. 

Esempio: [bounce.BounceThread](./src/main/java/bounce/BounceThread.java). Lo script di esecuzione dell'applicazione è `runBounceThread.bat`.

### Utilizzo di interrupt

Vedi esempio [bounce.BounceThreadWithStop](./src/main/java/bounce/BounceThreadWithStop.java) con bottone di *Stop* per terminare i thread in esecuzione. Lo script di esecuzione dell'applicazione è `runBounceThreadWithStop.bat`.

## Accesso a variabili condivise

La classe di tipo `Thread` per eseguire gli incrementi è:
[trythreads.concurrency.Incrementer](./src/main/java/trythreads/concurrency/Incrementer.java) che esegue l'incremento di oggetti di tipo [trythreads.concurrency.Counter](./src/main/java/trythreads/concurrency/Counter.java)

L'esecuzione di [trythreads.concurrency.TestUnsafeCounter](./src/main/java/trythreads/concurrency/TestUnsafeCounter.java) utilizza [trythreads.concurrency.CounterUnsafe](./src/main/java/trythreads/concurrency/CounterUnsafe.java) come implementazione di [trythreads.concurrency.Counter](./src/main/java/trythreads/concurrency/Counter.java).

Il programma attiva due thread ognuno dei quali accede alla variabile di tipo `trythreads.concurrency.Counter`, incrementandone il valore. L'accesso alla variabile è condiviso dai due thread.
 
Come si vede dall'esecuzione del programma, successive esecuzioni porta a risultati ogni volta diversi, anche si molto: il valore di `counter` dovrebbe essere la somma degli incrementi dei due thread, ma come si vede il valore non corrisponde alla somma, alcune volte ci sono notevoli differenze. 

Vedi sotto successive esecuzioni di [trythreads.concurrency.TestUnsafeCounter](./src/main/java/trythreads/concurrency/TestUnsafeCounter.java), utilzzando lo script di esecuzione `runConcurrentUnsafeCounter.bat`.

L'output di più esecuzioni:

```
SUM VALUE: 299.889.741 - SHOULD BE: 300.000.000
DEFFERENCE: 110.259 - DIFF: 0,036753 %
FINISHED Counter UNSAFE, elapsed time: 15 ms
```

```
SUM VALUE: 299.754.709 - SHOULD BE: 300.000.000
DEFFERENCE: 245.291 - DIFF: 0,081764 %
FINISHED Counter UNSAFE, elapsed time: 54 ms
```

```
SUM VALUE: 299.859.355 - SHOULD BE: 300.000.000
DEFFERENCE: 140.645 - DIFF: 0,046882 %
FINISHED Counter UNSAFE, elapsed time: 32 ms
```

L'esempio [trythreads.concurrency.TestSafeCounter](./src/main/java/trythreads/concurrency/TestSafeCounter.java) invece utilizza un'implementazione,[trythreads.concurrency.CounterSafe](./src/main/java/trythreads/concurrency/CounterSafe.java), di `trythreads.concurrency.Counter`, con i metodi sincronizzati tramite __syncronized__.

In questo caso, l'esecuzione della classe [trythreads.concurrency.TestSafeCounter](./src/main/java/trythreads/concurrency/TestSafeCounter.java) dà i risultati corretti. L'output di una esecuzione:


```
SUM VALUE: 300.000.000 - SHOULD BE: 300.000.000
DEFFERENCE: 0 - DIFF: 0,000000 %FINISHED Counter SAFE, elapsed time: 8.453 ms
```

Notate come la seconda versione che utilizza [trythreads.concurrency.CounterSafe](./src/main/java/trythreads/concurrency/CounterSafe.java) sia molto più lenta perché i metodi sono __synchronized__ e i thread che accedono devono acquisire il lock dell'oggetto prima di eseguire il metodo. Lo script di esecuzione è `runConcurrentSafeCounter.bat`..

## blocco synchronized 

Invece di sincronizzare tutto un metodo, come in [trythreads.concurrency.CounterUnsafe](./src/main/java/trythreads/concurrency/CounterUnsafe.java) è possibile sincronizzare solo alcuni blicchi di codice:

```java	
	// acquisizione lock di obj
	synchronized(obj) {
		// codicee
```

L'implementazione [trythreads.concurrency.CounterGeneric](./src/main/java/trythreads/concurrency/CounterGeneric.java) di `trythreads.concurrency.Counter`, utilizza i blocchi sincronizzati, per accedere alla variabile condivisa.

Il programma [trythreads.concurrency.TestGenericCounter](./src/main/java/trythreads/concurrency/TestGenericCounter.java), utilizza `trythreads.concurrency.CounterGeneric`. Lo script di esecuzione è `runConcurrentGenericCounter.bat`:

```
STARTING COUNTER NOT SYNCHRONIZED
SUM VALUE: 299.938.327 - SHOULD BE: 300.000.000
DEFFERENCE: 61.673 - DIFF: 0,020558 %
FINISHED Counter NOT SYNCHRONIZED, elapsed time: 31 ms

STARTING COUNTER SYNCHRONIZED
SUM VALUE: 300.000.000 - SHOULD BE: 300.000.000
DEFFERENCE: 0 - DIFF: 0,000000 %
FINISHED Counter SYNCHRONIZED, elapsed time: 8.412 ms
````
## Operazioni Atomiche

Infine classe di esempio con l'utilizzo delle operazioni atomiche:
[trythreads.concurrency.CounterAtomic](./src/main/java/trythreads/concurrency/CounterAtomic.java) chiamata dalla classe [trythreads.concurrency.TestAtomicCounter](./src/main/java/trythreads/concurrency/TestAtomicCounter.java). Lo script di esecuzione dell'applicazione è `runConcurrentAtomicCounter.bat`.

```
SUM VALUE: 300.000.000 - SHOULD BE: 300.000.000
DEFFERENCE: 0 - DIFF: 0,000000 %FINISHED Counter ATOMIC, elapsed time: 3.732 ms
```

