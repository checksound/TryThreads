# Try Threads

## Creazione di thread

* Esempio di creazione di thread come sottoclasse della classe `java.lang.Thread`: [trythreads.simple.MyThread](./src/main/java/trythreads/simple/MyThread.java);
* Esempio di crezione di thread tramite implemenentazione dell'interfaccia `java.lang.Runnable`: 
[trythreads.simple.MyThreadRunnable](./src/main/java/trythreads/simple/MyThreadRunnable.java);

## Utilizzo del metodo Thread.join

Il metodo [Thread.join()](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/Thread.html#join) aspetta che il thread abbia finito.

La classe [trythreads.simple.MyThreadWithoutJoin](./src/main/java/trythreads/simple/MyThreadWithoutJoin.java) esecuzione di un thread che esegue l'incremento di un contatore, senza l'utilizzo del metodo `join` per prendere il risultato alla fine dell'esecuzione del thread.

La classe [trythreads.simple.MyThreadWithJoin](./src/main/java/trythreads/simple/MyThreadWithJoin.java) esecuzione di un thread che esegue l'incremento di un contatore, mentre il main invocando il metodo `join` del thread aspetta il termine dell'esecuzione del thread.

## Accesso a variabili condivise

La classe di tipo `Thread` per eseguire gli incrementi è:
[trythreads.concurrency.Incrementer](./src/main/java/trythreads/concurrency/Incrementer.java) che esegue l'incremento di oggetti di tipo [trythreads.concurrency.Counter](./src/main/java/trythreads/concurrency/Counter.java)

L'esecuzione di [trythreads.concurrency.TestUnsafeCounter](./src/main/java/trythreads/concurrency/TestUnsafeCounter.java) utilizza [trythreads.concurrency.CounterUnsafe](./src/main/java/trythreads/concurrency/CounterUnsafe.java) come implementazione di [trythreads.concurrency.Counter](./src/main/java/trythreads/concurrency/Counter.java).

Il programma attiva due thread ognuno dei quali accede alla variabile di tipo `trythreads.concurrency.Counter`, incrementandone il valore. L'accesso alla variabile è condiviso dai due thread.
 
Come si vede dall'esecuzione del programma, successive esecuzioni porta a risultati ogni volta diversi, anche si molto: il valore di `counter` dovrebbe essere la somma degli incrementi dei due thread, ma come si vede il valore non corrisponde alla somma, alcune volte ci sono notevoli differenze. 

Vedi sotto successive esecuzioni di [trythreads.concurrency.TestUnsafeCounter](./src/main/java/trythreads/concurrency/TestUnsafeCounter.java):

```
SUM VALUE: 299.471.960 - SHOULD BE: 300.000.000
DEFFERENCE: 528.040 - DIFF: 0,176013 %
```

```
SUM VALUE: 299.925.606 - SHOULD BE: 300.000.000
DEFFERENCE: 74.394 - DIFF: 0,024798 %
```

```
SUM VALUE: 200.031.606 - SHOULD BE: 300.000.000
DEFFERENCE: 99.968.394 - DIFF: 33,322798 %
```

L'esempio [trythreads.concurrency.TestSafeCounter](./src/main/java/trythreads/concurrency/TestSafeCounter.java) invece utilizza un'implementazione,[trythreads.concurrency.CounterSafe](./src/main/java/trythreads/concurrency/CounterSafe.java), di `trythreads.concurrency.Counter`, con i metodi sincronizzati tramite __syncronized__.

In questo caso, l'esecuzione della classe [trythreads.concurrency.TestSafeCounter](./src/main/java/trythreads/concurrency/TestSafeCounter.java) dà i risultati corretti:

```
SUM VALUE: 300.000.000 - SHOULD BE: 300.000.000
DEFFERENCE: 0 - DIFF: 0,000000 %
```

Notate come la seconda versione che utilizza [trythreads.concurrency.CounterSafe](./src/main/java/trythreads/concurrency/CounterSafe.java) sia molto più lenta perché i metodi sono __synchronized__ e i thread che accedono devono acquisire il lock dell'oggetto prima di eseguire il metodo.

## blocco synchronized 

Invece di sincronizzare tutto un metodo, come in [trythreads.concurrency.CounterUnsafe](./src/main/java/trythreads/concurrency/CounterUnsafe.java) è possibile sincronizzare solo alcuni blicchi di codice:

```java
	
	// acquisizione lock di obj
	synchronized(obj) {
		// codice
	}
```

L'implementazione [trythreads.concurrency.CounterGeneric](./src/main/java/trythreads/concurrency/CounterGeneric.java) di `trythreads.concurrency.Counter`, utilizza i blocchi sincronizzati, per accedere alla variabile condivisa.

Il programma [trythreads.concurrency.TestGenericCounter](./src/main/java/trythreads/concurrency/TestGenericCounter.java), utilizza `trythreads.concurrency.CounterGeneric`.

```
STARTING COUNTER NOT SYNCHRONIZED
SUM VALUE: 299.938.327 - SHOULD BE: 300.000.000
DEFFERENCE: 61.673 - DIFF: 0,020558 %
FINISHED Counter NOT SYNCHRONIZED, elapsed time: 31 ms

STARTING COUNTER SYNCHRONIZED
SUM VALUE: 300.000.000 - SHOULD BE: 300.000.000
DEFFERENCE: 0 - DIFF: 0,000000 %
FINISHED Counter SYNCHRONIZED, elapsed time: 8.412 ms
```
