#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAXSIZE 20
#define MAXWEIGHT 10000

typedef struct ArcNode
{
	int adjvex;
	int weight;
	ArcNode* nextArc;
} ArcNode;

typedef struct VexNode
{
	char vertex;
	int id;
	ArcNode* firstArc;
} VexNode;

typedef struct ALGraph
{
	int vexNum;
	int arcNum;
	VexNode adjList[MAXSIZE];
} ALGraph;

typedef struct MGraph
{
	int vexNum;
	int arcNum;
	char vexs[MAXSIZE];
	int arcs[MAXSIZE][MAXSIZE];
} MGraph;

typedef struct Queue
{
	int front;
	int rear;
	int queueSize;
	int data[MAXSIZE];
} Queue;

void flushVisited(int visited[], int length);

void createGraph(MGraph* G);
void printGraph(MGraph G);
void dfTraver(MGraph G, int v, int visited[]);
void bfTraver(MGraph G, int v, int visited[]);
void hamilton(MGraph G, int visited[]);
void findCircle(MGraph G, int i, int visited[]);
void findSimplePath(MGraph G, int v, int s, char path[], int visited[], int* found);
void findShortestPath(MGraph G, int vi, int vj, int visited[]);

int initQueue(Queue* Q);
int emptyQueue(Queue Q);
int fullQueue(Queue Q);
int getHead(Queue Q, int* v);
int enQueue(Queue* Q, int v);
int deQueue(Queue* Q, int* v);

void main()
{
	int visited[MAXSIZE];
	char path[MAXSIZE] = { '\0' };
	int found = 0;
	MGraph mg;
	printf("创建邻接矩阵\n");
	createGraph(&mg);
	printf("\n邻接矩阵如下\n");
	printGraph(mg);
	printf("\n深度遍历序列:");
	flushVisited(visited, mg.vexNum);
	dfTraver(mg, 0, visited);
	printf("\n广度遍历序列:");
	flushVisited(visited, mg.vexNum);
	bfTraver(mg, 0, visited);
	printf("\n哈密尔顿链:");
	hamilton(mg, visited);
	printf("\n该图中是否有环\n");
	flushVisited(visited, mg.vexNum);
	findCircle(mg, 0, visited);
	printf("\n输出A到F之间的简单路径\n");
	flushVisited(visited, mg.vexNum);
	findSimplePath(mg, 0, 6, path, visited, &found);
	printf("%s\n", path);
	printf("\n输出A到F之间的最短路径\n");
	findShortestPath(mg, 0, 6, visited);

	system("pause");
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void flushVisited(int visited[], int length)
{
	register int i;

	for (i = 0; i < length; i++)
		visited[i] = 0;
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void createGraph(MGraph* G)
{
	register int i, j;
	int v1, v2, w;

	printf("请输入结点数和边数:");
	scanf("%d%d", &G->vexNum, &G->arcNum);
	getchar();

	printf("请输入结点信息:");
	for (i = 0; i < G->vexNum; i++)
		scanf("%c", &G->vexs[i]);
	getchar();

	for(i = 0; i < G->vexNum; i++)
		for (j = 0; j < G->vexNum; j++)
		{
			if (i == j)
				G->arcs[i][j] = 0;
			else
				G->arcs[i][j] = MAXWEIGHT;
		}

	for (i = 0; i < G->arcNum; i++)
	{
		printf("请输入第%d条边的起点和终点序号以及边权值:", i + 1);
		scanf("%d%d%d", &v1, &v2, &w);
		G->arcs[v1 - 1][v2 - 1] = w;
		G->arcs[v2 - 1][v1 - 1] = w;
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void printGraph(MGraph G)
{
	register int i, j;

	printf("\n————————————————————\n");
	for (i = 0; i < G.vexNum; i++)
	{
		for (j = 0; j < G.vexNum; j++)
			printf("%8d|", G.arcs[i][j]);
		printf("\n————————————————————\n");
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void dfTraver(MGraph G, int v, int visited[])
{
	register int i;
	printf("%c", G.vexs[v]);
	visited[v] = 1;
	for (i = 0; i < G.vexNum; i++)
		if (G.arcs[v][i] && G.arcs[v][i] < MAXWEIGHT && !visited[i])
			dfTraver(G, i, visited);
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void bfTraver(MGraph G, int v, int visited[])
{
	register int i, j;
	int w;
	Queue q;
	initQueue(&q);
	printf("%c", G.vexs[v]);
	visited[v] = 1;
	enQueue(&q, v);
	
	while (!emptyQueue(q))
	{
		deQueue(&q, &w);	

		for (i = 0; i < G.vexNum; i++)
			if (G.arcs[w][i] && G.arcs[w][i] < MAXWEIGHT && !visited[i])
			{
				printf("%c", G.vexs[i]);
				visited[i] = 1;
				enQueue(&q, i);
			}
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void DFS(MGraph G, int i, char path[], int visited[], int* n)
{
	int j;
	visited[i] = 1;
	path[*n] = G.vexs[i];
	(*n)++;
	if ((*n) == G.vexNum)
	{
		path[*n] = '\0';
		printf("%s", path);
		return;
	}
	for (j = 0; j < G.vexNum; j++)
		if (G.arcs[i][j] && G.arcs[i][j] < MAXWEIGHT && !visited[j])
			DFS(G, j, path, visited, n);
	visited[i] = 0;
	(*n)--;
}

void hamilton(MGraph G, int visited[])
{
	register int i;
	int n;
	char path[MAXSIZE];
	flushVisited(visited, G.vexNum);
	n = 0;
	for (i = 0; i < G.vexNum; i++)
	{
		if (!visited[i])
			DFS(G, i, path, visited, &n);
		if (strlen(path) == G.vexNum)
			break;
	}		
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void findCircle(MGraph G, int i, int visited[])
{
	register int j;
	int di, vi;
	visited[i] = 1;
	for (j = 0, di = 0, vi = 0; j < G.vexNum; j++)
		if (G.arcs[i][j] && G.arcs[i][j] < MAXWEIGHT)
		{
			di++;
			if (visited[j])
				vi++;
		}
	if (di == vi && di != 1)
	{
		printf("找到了一个环\n");
		return;
	}
	for (j = 0; j < G.vexNum; j++)
		if (G.arcs[i][j] && G.arcs[i][j] < MAXWEIGHT && !visited[j])
			findCircle(G, j, visited);
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void append(char path[], char ch)
{
	int length;
	length = strlen(path);
	path[length] = ch;
	path[length + 1] = '\0';
}

void rollback(char path[])
{
	int length;
	length = strlen(path);
	path[length - 1] = '\0';
}

void findSimplePath(MGraph G, int v, int s, char path[], int visited[], int* found)
{
	register int j;
	visited[v] = 1;
	append(path, G.vexs[v]);
	for(j = 0; j < G.vexNum && !(*found); j++)
		if (G.arcs[v][j] && G.arcs[v][j] < MAXWEIGHT)
		{
			if(j == s)
			{
				*found = 1;
				append(path, G.vexs[j]);
				visited[j] = 1;
			}
			if (visited[j] == 0)
				findSimplePath(G, j, s, path, visited, found);
		}
	if (!(*found))
		rollback(path);
}

///////////////////////////////////////////////////////////////////////////////////////////////////

void findShortestPath(MGraph G, int vi, int vj, int visited[])
{
	Queue q;
	int w, v;
	initQueue(&q);
	flushVisited(visited, G.vexNum);
	enQueue(&q, vi);
	visited[vi] = 1;
	while (!emptyQueue(q))
	{
		deQueue(&q, &v);
		printf("%c", G.vexs[v]);
		for(w = 0; w < G.vexNum; w++)
			if (G.arcs[v][w] && G.arcs[v][w] < MAXWEIGHT && !visited[w])
			{
				enQueue(&q, w);
				visited[w] = 1;
				if (w == vj)
					break;
			}
		if (w >= G.vexNum)
			continue;
	}
}

///////////////////////////////////////////////////////////////////////////////////////////////////

int initQueue(Queue* Q)
{
	Q->front = 0;
	Q->rear = 0;
	Q->queueSize = MAXSIZE;
	return 1;
}

int emptyQueue(Queue Q)
{
	if (Q.front == Q.rear)
		return 1;
	else 
		return 0;
}

int fullQueue(Queue Q)
{
	if ((Q.rear + 1) % Q.queueSize == Q.front)
		return 1;
	else
		return 0;
}

int getHead(Queue Q, int* v)
{
	if (emptyQueue(Q))
		return 0;
	*v = Q.data[Q.front];
	return 1;
}

int enQueue(Queue* Q, int v)
{
	if (fullQueue(*Q))
		return 0;
	Q->data[Q->rear] = v;
	Q->rear = (Q->rear + 1) % Q->queueSize;
	return 1;
}

int deQueue(Queue* Q, int* v)
{
	if (emptyQueue(*Q))
		return 0;
	*v = Q->data[Q->front];
	Q->front = (Q->front + 1) % Q->queueSize;
	return 1;
}