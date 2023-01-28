package weblab;

class SolutionHashTable extends HashTable {

    /**
     * Constructs a new HashTable.
     * Do NOT change this method.
     */
    public SolutionHashTable(int capacity) {
        super(capacity);
    }

    /**
     * Remove the entry associated with this key from the hash table.
     *
     * Returns false if the key is null.
     *
     * @param key
     *     String representing the key of the entry to remove.
     * @return true iff the entry has been successfully removed, else false.
     */
    public boolean remove(String key) {
        if (key == null) {return false;}

        int hash = hash(key); int idx;
        
        for (int i = 0; i < this.capacity; i++) {
            idx = (hash + i) % this.capacity; // makes it so we loop over the entire map starting at hash, ending at hash - 1
            if (this.getTable()[idx] == null) {
                return false;

            } else if (this.getTable()[idx].getKey() == null) {
                continue;
                
            } else if (this.getTable()[idx].getKey().equals(key)) {
                setDefunct(idx);
                return true;
            } 
        }
        // Should never get here, but else it doesn't compile
        return false;
    }
}
