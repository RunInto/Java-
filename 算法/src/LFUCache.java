/**
 * 无法寻找最近未使用key
 */
public class LFUCache {

    private Entry [] entrys; //缓存数组
    private int size; //缓存容量
    private int useSize = 0; //实际使用的容量
    private int intoNumOuter = 0;

    public LFUCache(int capacity) {
        this.size = capacity;
        this.entrys = new Entry[capacity];
    }

    public int get(int key) {
        int hash = hash(key);
        Entry entry = this.entrys[hash];
        if (key == entry.key) {
            entry.setUseCountIncre();
            return entry.value;
        }else {
            if (entry.next != null){
                return entry.next.get(key);
            }
        }
        return -1;
    }

    /**
     * 查找最近未使用键
     * @return
     */
    private int getLongNotUseKey() {
        int minUseCount = -1;
        int minUseCountKey = -1;
        for (int i = 0; i < entrys.length;++i) {
            if (entrys[i] != null) {
                minUseCount = entrys[i].useCount;
                minUseCountKey = entrys[i].key;
                break;
            }
        }

        Entry entry = null;
        for (int i = 0; i < entrys.length; ++i) {
            entry = entrys[i];
            if (entry != null) {
                if (entry.useCount <=minUseCount) {
                    minUseCount = entry.useCount;
                    minUseCountKey = entry.key;
                    while (entry != null) {
                        if (entry.next != null) {
                            //移除最近最未使用 用了 <= 如果有相等的也替换，则始终最小的是最接近的
                            if (entry.next.useCount <= minUseCount) {
                                minUseCount = entry.next.useCount;
                                minUseCountKey = entry.next.key;
                            }
                        }
                        entry = entry.next;
                    }

                }
            }

        }
        return minUseCountKey;
    }

    public void put(int key, int value) {
        int hash = hash(key);
        Entry entry = new Entry(key, value, hash);
        if (entrys[hash] == null) {
            //不冲突
            ++this.useSize;
            if (this.useSize <= this.size) {
                ++entry.useCount;
                entry.intoNumInner = ++this.intoNumOuter;
                entrys[hash] = entry;
            }else {
                //超出缓存大小使最近最不经常使用键失效
                int longNotUseKey =  getLongNotUseKey();
                //如果存在于数组，则简单索引处置为null即可
                int longNotUseKeyHash = hash(longNotUseKey);
                if (entrys[longNotUseKey] != null && entrys[longNotUseKey].key == longNotUseKey) {
                    entrys[longNotUseKey] = null;
                }


                //置空后，对应kv被删除，已使用大小减一
                --this.useSize;
            }
        }else {

            if (this.useSize >= this.size) {
                //往链表放之前也需要判断是否满了
                int longNotUseKey =  getLongNotUseKey();
                //如果存在于链表，则需要扫描链表，把指定位置的链表置为null
                Entry temp = entrys[longNotUseKey];
                while (temp != null) {
                    if (temp.next.key == key) {
                        temp.next = null;
                    }
                    temp = temp.next;
                }
                //置空后，对应kv被删除，已使用大小减一
                --this.useSize;
            }
            //两种情况 1 键重复 2 hash冲突
            //1 键重复
            if (key == entrys[hash].key) {
                //覆盖
                int useCount = entrys[hash].useCount;
                entry.useCount = useCount;
                ++entry.useCount;
                entrys[hash] = entry;
            }
            //2 hash冲突，往链表放
            ++this.useSize;
            entrys[hash].put(entry);
        }

    }

    /**
     * 封装kv
     */
    private class Entry {
        private int key;
        private int value;
        private int hash;
        private Entry next;
        //使用次数
        private int useCount=0;
        //插入时的序号，用来分辨最先最后插入，最大为缓存大小
        private int intoNumInner = 0;

        Entry() {

        }

        private void setUseCountIncre() {
            ++useCount;
        }

        private void setUseDown() {
            --useCount;
        }

        Entry(int key, int value, int hash) {
            this.key = key;
            this.value = value;
            this.hash = hash;
        }

        public void put(Entry entry) {
            if (this.next == null) {
//                ++LFUCache.this.useSize;
                if (LFUCache.this.useSize <= LFUCache.this.size) {
                    ++entry.useCount;
                    entry.intoNumInner = ++LFUCache.this.intoNumOuter;
                    this.next = entry;
                }else{
                    --LFUCache.this.useSize;
                }
            }else {
                //键重复
                if (this.next.key == entry.key) {
                    int useCount = this.next.useCount;
                    entry.useCount = ++useCount;
                    entry.intoNumInner = ++LFUCache.this.intoNumOuter;
                    this.next = entry;
                }
                //键冲突
                this.next.put(entry);
            }
        }

        public int get(int key) {
            if (this.key == key) {
                this.setUseCountIncre();
                return this.value;
            }
            if (this.next != null) {
                return this.next.get(key);
            }
            return -1;
        }
    }



    private int hash(int key) {
        return key % this.entrys.length;
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);
        cache.put(1,9);
        cache.put(2,8);
        cache.put(4,10);
        cache.put(5,11);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(2));
        System.out.println(cache.get(5));
    }

}