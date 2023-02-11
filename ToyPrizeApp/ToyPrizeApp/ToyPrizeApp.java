/**
* Необходимо написать проект, для розыгрыша в магазине игрушек.
* Функционал должен содержать добавление новых игрушек и задания веса
* для выпадения игрушек.
 */
package ToyPrizeApp;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class ToyPrizeApp {
    //сами игрушки
    private ArrayList<ToyRecord> toys;
    //очередь выпадения
    private PriorityQueue<ToyRecord> pq;

    public ToyPrizeApp(ArrayList <String> arr) throws ArrayIndexOutOfBoundsException
    {
        pq = new PriorityQueue<ToyRecord>();
        toys = new ArrayList<ToyRecord>();

        //по заданию зачем-то надо в конструкторе принимать строки, 
        //поэтому преобразование реализуем здесь
        arr.forEach((str) -> {
            // тут формируется не менее трех массивов по заданию
            String[] toy_raw = str.split("\\t", 3);
            try {
                ToyRecord tr = new ToyRecord(
                    Long.parseLong(toy_raw[0]), 
                    toy_raw[2],
                    Double.parseDouble(toy_raw[1]) 
                    );
                this.put(tr);
            }
            catch(Exception e) {
                throw new ArrayIndexOutOfBoundsException("Parsing error");
              }
          });
    }

    public ToyRecord get(){
        //Моделируем розыгрыш.
        //По заданию обязательно использовать PriorityQueue,
        //поэтому добавляем элементы в очередь 
        //со случайным весом, нормированным на 1.
        //Естественно, нормировать на вес быстрее, но зачем тогда PQ - не ясно
        //Можно было бы добавлять элементы в put и брать из очереди по одному,
        //но тогда есть риск исчерпания очереди.
        if(pq.size() == 0){
            toys.forEach((tr) -> {
                for(int i = 0; i < tr.getWeight(); i++)
                {
                    ToyRecord tr_rnd = new ToyRecord(
                            tr.getId(), 
                            "",
                            Math.random()
                            );
                    pq.add(tr_rnd);
                }
            });
        }
        return pq.poll();
    }

    public ArrayList<Long> getIdList()
    {
        ArrayList<Long> res = new ArrayList<>(toys.size());
        toys.forEach((tr) -> {
            res.add(tr.getId());
        });
        return res;
    }

    private void put(ToyRecord tr){
        toys.add(tr);
    }

}
