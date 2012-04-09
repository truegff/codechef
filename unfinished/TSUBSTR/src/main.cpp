#include <cstdio>
#include <map>
#include <string>

using namespace std;

class Letter {
public:
    char ch;
    bool visited;
    map<char, Letter*> children;
    Letter() {
        //ch=' ';
        visited = false;
    }
};

class Index {
public:
    char value;
    Index* parent;
    long long size;
private:
    map<char, Index*> index;
public:
    Index(Index *parent) {
        size = 1;
        this->parent = parent;
    }
    Index* addElement(char key) {
        Index* ptr = new Index(this);
        index[key] = ptr;
        size++;
        return ptr;
    }
    Index* getElementOrCreate(char key) {
        if (index.count(key)==0) return addElement(key);
        return index[key];
    }
    Index* getElement(char key) {
        if (index.count(key)==0) return NULL;
        return index[key];
    }
};

void subStringGenerator(Index *index, Letter *root) {
    index->value=root->ch;

    map<char, Letter*>::iterator it;
    for (it=root->children.begin(); it!=root->children.end(); it++) {
        Letter *successor = it->second;
        Index *subIndex = index->getElementOrCreate(successor->ch);
        int subSize = subIndex->size;
        subStringGenerator(subIndex, successor);
        index->size += subIndex->size - subSize;
    }
}

string getString(Index *index, char order[], int K) {
    if (K==1) {
        string sb;
        while (index->parent != NULL) {
            sb=index->value+sb;
            index=index->parent;
        }
        return sb;
    }
    K--;
    Index *subIndex;
    for (int i=0; i<26; i++) {
        subIndex = index->getElement(order[i]);
        if (subIndex == NULL) continue;
        if (subIndex->size < K) {
            K-=subIndex->size;
        } else break;
    }
    return getString(subIndex, order, K);
}

int main(void) {
    int N=0,Q=0;
    scanf("%d", &N);
    scanf("%d", &Q);
    getchar();

    //reading given tree
    //and building the index

    Letter **letters = new Letter*[N];
    //Letter *letters[N];
    char *alphabet = new char[N];
    scanf("%s", alphabet);
    for (int i=0; i<N; i++) {
        Letter *created = new Letter();
        created->ch = alphabet[i];
        letters[i]=created;
    }

    (void)getchar();

    int a=0, b=0;
    for (int i=0; i<N-1; i++) {
        scanf("%d", &a);
        scanf("%d", &b);
        Letter *child = letters[b-1];
        Letter *parent = letters[a-1];

        //uniting duplicate children by the way
        if (parent->children.count(child->ch)==0) {
            parent->children[child->ch]= child;
        } else {
            letters[b-1] = parent->children[child->ch];
        }
        (void)getchar();
    }

    Index *index = new Index(NULL);

    index->value=' ';
    for (int i=0; i<N; i++) {
        Letter *letter = letters[i];
        if (!letter->visited) {
            letter->visited=true;
            Index* subIndex = index->getElementOrCreate(letter->ch);
            long long subSize = subIndex->size;
            subStringGenerator(subIndex, letter);
            index->size += subIndex->size - subSize;
        }
    }

    printf("%lld\n", index->size);

    long long K=0;
    char *order = new char[27];
    for (int i=0; i<Q; i++) {
        //for (int j=0; j<26; j++) {
            scanf("%s", order);
        //}
        (void)getchar();
        scanf("%lld", &K);
        (void)getchar();
        if (K>index->size) {
            printf("-1\n");
            continue;
        }

        string stepAnswer = "";
        stepAnswer = getString(index, order, K);

        printf("%s\n", stepAnswer.c_str());
    }
    return 0;
}
