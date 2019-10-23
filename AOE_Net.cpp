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
	
	printf("邻接表创建图\n");
	createALGraph(&g);
	printf("\n");
	printf("打印邻接表\n");
	printALGraph(g);
	printf("\n");
	printf("深度遍历序列:");
	dfTraverUnaccess(g, visited);
	printf("\n");
	printf("广度遍历序列:");
	bfTraverUnaccess(g, visited);
	printf("\n");
	printf("拓扑排序:");
	topSort(g);
	printf("\n");
	printf("寻找关键事件:");
	criticalPath(g);
	printf("\n");
	system("pause");
}

//清空遍历数组

void flushVisited(int visited[], int length)
{
	register int i;
	for (i = 0; i < length; i++)
		visited[i] = 0;
}

//创建邻接表

void createALGraph(ALGraph* G)
{
	register int i, j, k, w;
	ArcNode* p;

	printf("请输入结点数和弧数:");
	scanf("%d%d", &G->vexNum, &G->arcNum);
	printf("请输入结点信息:");
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
		printf("请输入相邻结点信息及弧的权值:");
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

//打印邻接表

void printALGraph(ALGraph G)
{
	register int i;
	ArcNode* p = NULL;
	for (i = 0; i < G.vexNum; i++)
	{
		p = G.adjList[i].firstArc;
		printf("%d——入度为%d——", i, G.adjList[i].id);
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
	printf("顶点数为%d\n", G.vexNum);
	printf("边数为%d\n", G.arcNum);
}

//深度遍历

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

//广度遍历

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

//拓扑排序

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

//关键路径

void criticalPath(ALGraph G)
{
	Stack s1;			//入度为零结点栈
	Stack s2;			//拓扑序列栈
	ArcNode* p;
	int ve[MAXSIZE];	//事件的最早发生时间
	int vl[MAXSIZE];	//事件的最晚发生时间
	int ee;				//活动的最早发生时间
	int el;				//活动的最晚发生时间

	int j;
	int k;
	char flag;
	register int i;
	for (i = 0; i < G.vexNum; i++)	//初始化ve=0
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

	for (i = 0; i < G.vexNum; i++)	//初始化vl=ve[n]
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
			printf("%c--%d-->%c,活动最早发生时间:%d,活动最晚发生时间:%d,是否为关键事件:%c\n",
				G.adjList[j].vertex, p->weight, G.adjList[k].vertex, ee, el, flag);
			p = p->nextArc;
		}
	}
}

/*
 *	队列相关操作
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
 * 栈相关操作
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