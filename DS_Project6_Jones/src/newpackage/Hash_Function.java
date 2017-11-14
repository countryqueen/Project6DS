/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.Map;
import java.lang.Object;
import static java.nio.file.Files.list;
import static java.rmi.Naming.list;
import java.util.*;
import java.util.Scanner;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.concurrent.ConcurrentHashMap.MapEntry;
import static jdk.nashorn.internal.objects.NativeArray.map;

/**
 *
 * @author Owner
 */
public class Hash_Function
<K,V> extends Dictionary <K,V> implements Map <K,V>, Cloneable
{
   
    protected MapEntry[] map;
    //1000 elemtents for arrayu
    protected final int DECAP= 1000;
    protected final double DEFLOAD = .75;
    
    protected int origCap;
    //this is the original capacity
    protected int currCap;
    protected double load;
    
    protected int numPairs = 0;
    
    public HMap()
    {
        currCap = DECAP;
        load = DEFLOAD;
    }
    
    public HMap(int initCap, double initLoad)
    {
        map = new MapEntry[initCap];
        origCap = initCap;
        currCap = initCap;
        load = initLoad;
    }
    
    //incrememtns the capacity of the map by an amount
    //equal to the original capacity
    private void enlarge()
    {
        //create a snapshot iterator of the map and save the current size
        Iterator<MapEntry<K,V>> i = iterator();
        int count = numPairs;
    }
    
    //create the larger array and reset the variables
    map = new MapEntry[currCap + origCap];
    currCap =currCap + origCap:
    numPairs = 0;
    
    
    //put contents of current map into larger array
    MapEntry entry;
    for (int n = 1; n <= count; n++)
    {
        entry = i.next();
        this.put((K)entry.getKey(), (V)entry.getValue());
    }
    
    public V put(K k, V v)
    {
        if(k == null)
        {
            throw new IllegalArgumentException("Maps do not allow null keys");
        }
        MapEntry<K, V> entry = new MapEntry<K,V>(k,v);
        
        int location = Math.abs(k.hashCode()) % currCap;
        while((map[location] !=null) && !(map[location].getKey().equals(k)))
        {
            location = (location + 1) % currCap;
        }
        if(map[location] == null)
        {
            map[location] = entry;
            numPairs++;
            if((float)numPairs/currCap > load)
            {
                enlarge();
            }
            return null;
        }
        else
        {
            V temp = (V)map[location].getValue();
            map[location] = entry;
            return temp;
        }
            
    }
    
    public V get(K k)
    {
        if (k ==null)
        {
            throw new IllegalArgumentException("Maps do not allow null keys."); 
        }
        int location = Math.abs(k.hashCode()) % currCap;
        while ((map[location] !=null) && !(map[location].getKey().equals(k)))
        {
            location =(location + 1) % currCap;
                    
        }
        if (map[location] == null)
        {
            return null;
        }
        else
        {
            return (V)map[location].getValue();
        }
            
    }
    
    public V remove(K k)
    {
        throw new UnsupportedOperationException("HMAP does not allow remove");
    }
    
    public boolean contains (K k)
    {
        if(k==null)
        {
            throw new IllegalArgumentException("Maps do not allow null keys.");
        }
        int location = Math.abs(k.hashCode()) % currCap;
        while(map[location] != null)
        {
            if (map[location].getKey().equals(k))
            {
                return true;
            }
            else
            {
                location = (location + 1) % currCap;
            }
        }
        return false;
        
    }
    
    public boolean isEmpty()
    {
        return (numPairs == 0);
    }
    
    public boolean isFull()
    {
        return false;
    }
    
    public int size()
    {
        return numPairs;
    }
    private class MapIterator implements Iterator<MapEntry<K,V>>
    {
        int listSize = size();
        private MapEntry[] list = new MapEntry[listSize];
        private int previousPos = -1;
        
    }
    public MapIterator()
    {
        int next  = -1;
        for (int i =0; i < listSize ; i++)
        {
            next ++;
            while(map[next] == null)
            {
                next++ ;
            }
            list[i] = map[next];
        }
    }    
    public boolean hasNext()
    {
        return(previousPos < (listSize - 1));
    }
    
    public MapEntry<K,V> next()
    {
        if(!hasNext())
        {
            throw new IndexOutOfBoundsException("illegal invocation of next " + " in HMap iterator. \n");
        }
        previousPos++;
        return list[previousPos];
    }
    
    public void remove()
    {
        throw new UnsupportedOperationException("Unsupported remove attempted " +"on HMap iterator. \n" );
    }
    

    public Iterator<MapEntry<K,V>> iterator()
    {
        return new MapIterator();
    }
}