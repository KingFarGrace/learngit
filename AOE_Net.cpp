#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define MAXSIZE 20

typedef struct ArcNode
{
	int adjvex;
	int weight;
	struct ArcNode* nextArc;
}ArcNode;

typedef struct VertexNode
{
	int id;
	char vertex;
	ArcNode* firstArc;
}VertexNode;

typedef struct ALGraph
{
	VertexNode adjList[MAXSIZE];
	int vexNum;
	int arcNum;
}ALGraph;

typedef struct
{
	int front;
	int rear;
	int queueSize;
	int data[MAXSIZE];
}Queue;

typedef struct
{
	int data[MAXSIZE];
	int top;
	int stackSize;
}Stack;

void flushVisited(int visited[], int length);
void createALGraph(ALGraph* G);
void printALGraph(ALGraph G);
void dfTraver(ALGraph G, int v, int visited[]);
void dfTraverUnaccess(ALGraph G, int visited[]);
void bfTraver(ALGraph G, int v, int visited[]);
void bfTraverUnaccess(ALGraph G, int visited[]);
int topSort(ALGraph G);
void criticalPath(ALGraph G);

int initQueue(Queue* Q);
int queueEmpty(Queue Q);
int getHead(Queue Q, int* v);
int enQueue(Queue* Q, int v);
int deQueue(Queue* Q, int* v);

int initStack(Stack* S);
int stackEmpty(Stack S);
int getTop(Stack S, int* v);
int push(Stack* S, int v);
int pop(Stack* S, int* v);

//main

void main()
{
	int visited[MAXSIZE];
	ALGraph g;
	
	printf("�ڽӱ���ͼ\n");
	createALGraph(&g);
	printf("\n");
	printf("��ӡ�ڽӱ�\n");
	printALGraph(g);
	printf("\n");
	printf("��ȱ�������:");
	dfTraverUnaccess(g, visited);
	printf("\n");
	printf("��ȱ�������:");
	bfTraverUnaccess(g, visited);
	printf("\n");
	printf("��������:");
	topSort(g);
	printf("\n");
	printf("Ѱ�ҹؼ��¼�:");
	criticalPath(g);
	printf("\n");
	system("pause");
}

//��ձ�������

void flushVisited(int visited[], int length)
{
	register int i;
	for (i = 0; i < length; i++)
		visited[i] = 0;
}

//�����ڽӱ�

void createALGraph(ALGraph* G)
{
	register int i, j, k, w;
	ArcNode* p;

	printf("�����������ͻ���:");
	scanf("%d%d", &G->vexNum, &G->arcNum);
	printf("����������Ϣ:");
	getchar();

	for (i = 0; i < G->vexNum; i++)
	{
		scanf("%c", &G->adjList[i].vertex);
		G->adjList[i].firstArc = NULL;
		G->adjList[i].id = 0;
	}
	getchar();

	for (k = 0; k < G->arcNum; k++)
	{
		printf("���������ڽ����Ϣ������Ȩֵ:");
		scanf("%d%d%d", &i, &j, &w);
		getchar();
		p = new ArcNode;
		p->weight = w;
		p->adjvex = j - 1;
		p->nextArc = G->adjList[i - 1].firstArc;
		G->adjList[i - 1].firstArc = p;
		G->adjList[p->adjvex].id++;
	}
}

//��ӡ�ڽӱ�

void printALGraph(ALGraph G)
{
	register int i;
	ArcNode* p = NULL;
	for (i = 0; i < G.vexNum; i++)
	{
		p = G.adjList[i].firstArc;
		printf("%d�������Ϊ%d����", i, G.adjList[i].id);
		printf("%c", G.adjList[i].vertex);

		while (p)
		{
			if (p)
				printf("<--%d--",p->weight);

			printf("%c", G.adjList[p->adjvex].vertex);
			p = p->nextArc;
		}//end_while

		printf("\n");
	}//end_for
	printf("������Ϊ%d\n", G.vexNum);
	printf("����Ϊ%d\n", G.arcNum);
}

//��ȱ���

void dfTraver(ALGraph G, int v, int visited[])
{
	ArcNode* p = G.adjList[v].firstArc;
	printf("%c", G.adjList[v].vertex);
	visited[v] = 1;
	while (p)
	{
		if (visited[p->adjvex] == 0)
			dfTraver(G, p->adjvex, visited);
		p = p->nextArc;
	}
}

void dfTraverUnaccess(ALGraph G, int visited[])
{
	int v;
	flushVisited(visited, G.vexNum);
	for (v = 0; v < G.vexNum; v++)
		if (!visited[v])
			dfTraver(G, v, visited);
	printf("\n");
}

//��ȱ���

void bfTraver(ALGraph G, int v, int visited[])
{
	Queue q;
	ArcNode* p;
	int w;
	printf("%c", G.adjList[v].vertex);
	initQueue(&q);
	enQueue(&q, v);

	while (!queueEmpty(q))
	{
		deQueue(&q, &v);
		p = G.adjList[v].firstArc;
		visited[v] = 1;

		while (p != NULL)
		{
			w = p->adjvex;
			if (visited[w] == 0)
			{
				printf("%c", G.adjList[w].vertex);
				visited[w] = 1;
				enQueue(&q, w);
			}
			p = p->nextArc;
		}
	}
}

void bfTraverUnaccess(ALGraph G, int visited[])
{
	int v;
	flushVisited(visited, G.vexNum);
	for (v = 0; v < G.vexNum; v++)
		if (!visited[v])
			bfTraver(G, v, visited);
	printf("\n");
}

//��������

int topSort(ALGraph G)
{
	int m = 0;
	int v;
	int w;
	register int i;
	ArcNode* p;
	Stack s;
	initStack(&s);
	for (i = 0; i < G.vexNum; i++)
		if (G.adjList[i].id == 0)
			push(&s, i);
	while (!stackEmpty(s))
	{
		pop(&s, &v);
		printf("%c", G.adjList[v].vertex);
		m++;
		p = G.adjList[v].firstArc;
		while (p)
		{
			w = p->adjvex;
			G.adjList[w].id--;
			if (G.adjList[w].id == 0)
				push(&s, w);
			p = p->nextArc;
		}
	}
	printf("\n");
	if (m < G.vexNum)
		return 0;
	else
		return 1;
}

//�ؼ�·��

void criticalPath(ALGraph G)
{
	Stack s1;			//���Ϊ����ջ
	Stack s2;			//��������ջ
	ArcNode* p;
	int ve[MAXSIZE];	//�¼������緢��ʱ��
	int vl[MAXSIZE];	//�¼���������ʱ��
	int ee;				//������緢��ʱ��
	int el;				//���������ʱ��

	int j;
	int k;
	char flag;
	register int i;
	for (i = 0; i < G.vexNum; i++)	//��ʼ��ve=0
		ve[i] = 0;

	initStack(&s1);
	initStack(&s2);
	for (i = 0; i < G.vexNum; i++)
		if (G.adjList[i].id == 0)
			push(&s1, i);
	while (!stackEmpty(s1))
	{
		pop(&s1, &j);
		push(&s2, j);
		p = G.adjList[j].firstArc;
		while (p)
		{
			k = p->adjvex;
			G.adjList[k].id--;
			if (G.adjList[k].id == 0)
				push(&s1, k);
			if (ve[j] + p->weight > ve[k])
				ve[k] = ve[j] + p->weight;
			p = p->nextArc;
		}
	}

	for (i = 0; i < G.vexNum; i++)	//��ʼ��vl=ve[n]
		vl[i] = ve[G.vexNum - 1];

	while (!stackEmpty(s2))
	{
		pop(&s2, &j);
		p = G.adjList[j].firstArc;
		while (p)
		{
			k = p->adjvex;
			if ((vl[k] - p->weight) < vl[j])
				vl[j] = vl[k] - p->weight;
			p = p->nextArc;
		}
	}

	for (j = 0; j < G.vexNum; j++)
	{
		p = G.adjList[j].firstArc;
		while (p)
		{
			k = p->adjvex;
			ee = ve[j];
			el = vl[k] - p->weight;
			if (ee == el)
				flag = 'Y';
			else
				flag = 'N';
			printf("%c--%d-->%c,����緢��ʱ��:%d,�������ʱ��:%d,�Ƿ�Ϊ�ؼ��¼�:%c\n",
				G.adjList[j].vertex, p->weight, G.adjList[k].vertex, ee, el, flag);
			p = p->nextArc;
		}
	}
}

/*
 *	������ز���
 */

int initQueue(Queue* Q)
{
	Q->front = 0;
	Q->rear = 0;
	Q->queueSize = MAXSIZE;
	return 1;
}

int queueEmpty(Queue Q)
{
	if (Q.front == Q.rear)
		return 1;
	else
		return 0;
}

int getHead(Queue Q, int* v)
{
	if (queueEmpty(Q))
		return 0;
	*v = Q.data[Q.front];
	return 1;
}

int enQueue(Queue* Q, int v)
{
	if ((Q->rear + 1) % Q->queueSize == Q->front)
		return 0;
	Q->data[Q->rear] = v;
	Q->rear = (Q->rear + 1) % Q->queueSize;
	return 1;
}

int deQueue(Queue* Q, int* v)
{
	if (queueEmpty(*Q))
		return 0;
	*v = Q->data[Q->front];
	Q->front = (Q->front + 1) % Q->queueSize;
	return 1;
}

/*
 * ջ��ز���
 */

int initStack(Stack* S)
{
	S->top = -1;
	S->stackSize = MAXSIZE;
	return 1;
}

int stackEmpty(Stack S)
{
	if (S.top == -1)
		return 1;
	else
		return 0;
}

int getTop(Stack S, int* v)
{
	if (stackEmpty(S))
		return 0;
	*v = S.data[S.top];
	return 1;
}

int push(Stack* S, int v)
{
	if (S->stackSize == S->top + 1)
		return 0;
	S->top++;
	S->data[S->top] = v;
	return 1;
}

int pop(Stack* S, int* v)
{
	if (stackEmpty(*S))
		return 0;
	*v = S->data[S->top];
	S->top--;
	return 1;
}