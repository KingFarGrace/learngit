#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define MAXSIZE 10

typedef char ZFC[MAXSIZE];

typedef struct
{
	int front;
	int rear;
	int queueSize;
	int data[MAXSIZE];
}Queue;

typedef struct
{
	int vi;
	int weight;
}LowCost;

typedef struct ArcNode
{
	int adjvex;
	int weight;
	struct ArcNode* nextArc;
}ArcNode;

typedef struct
{
	char vertex;
	ArcNode* firstArc;
}VexNode;

typedef struct
{
	VexNode adjList[MAXSIZE];
	int vexNum, arcNum;
}ALGraph;

typedef struct
{
	int vexNum, arcNum;
	char vexs[MAXSIZE];
	int arcs[MAXSIZE][MAXSIZE];
}MGraph;

void menu(void);
void flushVisited(int visited[], int length);

void createMGraph(MGraph *G);
void printMatrix(MGraph G);
void createALGraph(ALGraph* G);
void printALGraph(ALGraph G);

void prim(MGraph G);
void kruscal(ALGraph G);
int find(ZFC* s, int n, char c);
int checkExistArc(int arcNum, int arcBox[][2], int i, int j);
void dijkstra(MGraph  G, int v);
void hamilton(MGraph G, int visited[]);
void DFS(MGraph G, int i, int path[], int visited[], int* n);
void dfTraverAlg(ALGraph G, int v, int visited[]);
void dfTraverUnaccessAlg(ALGraph G, int visited[]);
void bfTraverAlg(ALGraph G, int v, int visited[]);
void bfTraverUnaccessAlg(ALGraph G, int visited[]);
void dfTraverMg(MGraph G, int v, int visited[]);
void dfTraverUnaccessMg(MGraph G, int visited[]);
void bfTraverMg(MGraph G, int v, int visited[]);
void bfTraverUnaccessMg(MGraph G, int visited[]);

int initQueue(Queue* Q);
int queueEmpty(Queue Q);
int enQueue(Queue* Q, int v);
int deQueue(Queue* Q, int* v);

/*
 *	main
 */
int main()
{
	menu();
	system("pause");
	return 0;
}

/*
 *	menu
 */
void menu(void)
{
	int judge = 0, v = 0;
	int visited[MAXSIZE] = { 0 };
	MGraph mg;
	ALGraph alg;
	while (1)
	{
		printf("图的算法\n");
		printf("1.创建邻接矩阵	2.创建邻接表\n");
		printf("3.prim		4.kruscal\n");
		printf("5.dijkstra\n");
		printf("6.打印邻接矩阵	7.打印邻接表\n");
		printf("8.邻接表深度遍历	9.邻接表广度遍历\n");
		printf("10.邻接矩阵深度遍历	11.邻接矩阵广度遍历\n");
		printf("请选择操作（输入0退出）:");
		scanf("%d", &judge);
		if (judge == 0)
			break;
		switch (judge)
		{
			case 1:	createMGraph(&mg);
					break;

			case 2:	createALGraph(&alg);
					break;

			case 3:	prim(mg);
					break;

			case 4:	kruscal(alg);
					break;

			case 5:	printf("请输入终点的序号:");
					scanf("%d", &v);
					dijkstra(mg, v-1);
					break;

			case 6:	printMatrix(mg);
					break;

			case 7:	printALGraph(alg);
					break;

			case 8:	dfTraverUnaccessAlg(alg, visited);
					break;

			case 9:	bfTraverUnaccessAlg(alg, visited);
					break;

			case 10:	dfTraverUnaccessMg(mg, visited);
						break;

			case 11:	bfTraverUnaccessMg(mg, visited);
						break;
		}
	}
}

/*
 *
 */
void flushVisited(int visited[], int length)
{
	register int i;
	for (i = 0; i < length; i++)
	{
		visited[i] = 0;
	}
}

/*
 	邻接矩阵创建图
	*/
void createMGraph(MGraph* G)
{
	register int i, j, k;
	int w;
	printf("请输入结点数和边数:");
	scanf("%d%d",&G->vexNum,&G->arcNum);
	getchar();

	printf("请输入结点信息:\n");
	for (i = 0; i < G->vexNum; i++)
	{
		scanf("%c",&G->vexs[i]);
		getchar();
	}

	for(i=0;i<G->vexNum;i++)
		for (j = 0; j < G->vexNum; j++)
		{
			if (i == j)
				G->arcs[i][j] = 0;
			else
				G->arcs[i][j] = 10000;
		}

	for (k = 0; k < G->arcNum; k++)
	{
		printf("请输入相连结点序号及弧的权值:");
		scanf("%d%d%d",&i,&j,&w);
		G->arcs[i - 1][j - 1] = w;
		G->arcs[j - 1][i - 1] = w;
	}
}

/*
	打印邻接矩阵
	*/
void printMatrix(MGraph G)
{
	register int i, j;

	printf("邻接矩阵如下\n");
	printf("————————————————");
	printf("\n");
	printf(" |");
	for (i = 0; i < G.vexNum; i++)
		printf("%6c|", G.vexs[i]);
	printf("\n");
	printf("————————————————");
	printf("\n");
	for (i = 0; i < G.vexNum; i++)
	{
		printf("%c|", G.vexs[i]);
		for (j = 0; j < G.vexNum; j++)
			printf("%6d|", G.arcs[i][j]);
		printf("\n");
		printf("————————————————");
		printf("\n");
	}
}

/*
	邻接表创建图
	*/
void createALGraph(ALGraph* G)
{
	register int i;
	int vex1, vex2, w;
	ArcNode* p, * q;
	printf("请输入结点数和边数:");
	scanf("%d%d", &G->vexNum, &G->arcNum);
	getchar();
	printf("请输入结点信息:");
	for (i = 0; i < G->vexNum; i++)
	{
		scanf("%c",&G->adjList[i].vertex);
		G->adjList[i].firstArc = NULL;
	}
	getchar();
	printf("请输入相邻结点序号及弧的权值:\n");
	for (i = 0; i < G->arcNum; i++)
	{
		scanf("%d%d%d", &vex1, &vex2, &w);
		p = new ArcNode;
		q = new ArcNode;
		p->adjvex = vex2 - 1;
		p->weight = w;
		q->weight = w;
		q->adjvex = vex1 - 1;
		p->nextArc = G->adjList[vex1 - 1].firstArc;
		q->nextArc = G->adjList[vex2 - 1].firstArc;
		G->adjList[vex1 - 1].firstArc = p;
		G->adjList[vex2 - 1].firstArc = q;
	}
}

/*
	打印邻接表
	*/
void printALGraph(ALGraph G)
{
	register int i;
	ArcNode* p = NULL;
	for (i = 0; i < G.vexNum; i++)
	{
		p = G.adjList[i].firstArc;
		printf("%d——", i);
		printf("%c", G.adjList[i].vertex);


		while (p)
		{
			if (p)
				printf("<--");

			printf("%d", p->adjvex);
			p = p->nextArc;
		}//end_while

		printf("\n");
	}//end_for
	printf("顶点数为%d\n", G.vexNum);
	printf("边数为%d\n", G.arcNum);
}

/*
	prim
	*/
void prim(MGraph G) 
{
	int minCost;
	register int i, j, k;
	LowCost* lc = (LowCost *)malloc(sizeof(LowCost) * G.vexNum);

	for (i = 0; i < G.vexNum; i++)
	{
		lc[i].vi = 0;
		lc[i].weight = G.arcs[0][i];
	}
	lc[0].weight = 0;

	for (i = 1; i < G.vexNum; i++) 
	{
		minCost = 10000;

		for (j = 0; j < G.vexNum; j++)
			if (lc[j].weight < minCost && lc[j].weight)
			{
				minCost = lc[j].weight;
				k = j;
			}
		printf("%c--%d-->%c\n", G.vexs[lc[k].vi], minCost, G.vexs[k]);
		lc[k].weight = 0;

		for (j = 0; j < G.vexNum; j++)
			if (G.arcs[k][j] < lc[j].weight && G.arcs[k][j])
			{
				lc[j].weight = G.arcs[k][j];
				lc[j].vi = k;
			}					
	}
}

/*
	kruscal
	*/
void kruscal(ALGraph G)
{
	register int i, j, k, t, m, n;
	int x[MAXSIZE][4];
	int arcBox[MAXSIZE][2] = { 0 };
	ArcNode* p;
	ZFC c[MAXSIZE];

	for (i = 0; i < G.vexNum; i++)
	{
		c[i][0] = i + 48;
		c[i][1] = 0;
	}
	
	
	for (k = 0, j = 0; j < G.vexNum; j++)
	{
		p = G.adjList[j].firstArc;
		while (p != NULL) 
		{
			if (!checkExistArc(G.arcNum, arcBox, j, p->adjvex))
			{
				x[k][0] = j;
				x[k][1] = p->adjvex;
				x[k][2] = p->weight;
				x[k][3] = 0;			
				arcBox[k][0] = j;
				arcBox[k][1] = p->adjvex;
				k++;
			}
			p = p->nextArc;
		}
	}
	
	for (i = 0; i < G.arcNum; i++)
		for (j = 0; j < G.arcNum - 1 - i; j++)
			if (x[j][2] > x[j + 1][2])
				for (m = 0; m < 4; m++)
				{
					t = x[j][m];
					x[j][m] = x[j + 1][m];
					x[j + 1][m] = t;
				}
	
	for (i = 0, k = 0; i < G.arcNum; i++)
	{
		m = find(c, G.vexNum, x[i][0] + 48);
		n = find(c, G.vexNum, x[i][1] + 48);

		if (m != n)
		{
			strcat(c[m], c[n]);
			c[n][0] = 0;
			k++;
			x[i][3] = 1;
			if (k == G.vexNum - 1)
				break;
		}
	}

	for (i = 0; i < G.arcNum; i++)
		if (x[i][3] == 1)
			printf("%c--%d-->%c\n", G.adjList[x[i][0]].vertex, x[i][2], G.adjList[x[i][1]].vertex);
}

//寻找结点所属连通分量
int find(ZFC* s, int n, char c)
{
	register int i, j;
	for (i = 0; i < n; i++)
		for (j = 0; j < strlen(s[i]); j++)
			if (s[i][j] == c)
				return i;
	return -1;
}

//边是否被遍历
int checkExistArc(int arcNum, int arcBox[][2], int i, int j)
{
	register int k;
	for (k = 0; k < arcNum; k++)
		if (j == arcBox[k][0] && i == arcBox[k][1])
			return 1;
	return 0;
}

/*
	迪杰斯特拉！！！！！！！！！
	*/
void dijkstra(MGraph  G, int v)
{
	int dist[MAXSIZE];
	int path[MAXSIZE][MAXSIZE];
	int i, j, k, m, min, n, flag;

	for (i = 0; i < G.vexNum; i++)
		for (j = 0; j < G.vexNum; j++)
			path[i][j] = -1;

	for (i = 0; i < G.vexNum; i++)
	{
		dist[i] = G.arcs[v][i];
		if (dist[i] != 0 && dist[i] < 10000)
			path[i][0] = v;
	}

	dist[v] = 0;
	n = 0;
	flag = 1;
	while (flag)
	{
		k = 0;
		min = 10000;

		for (j = 0; j < G.vexNum; j++)
			if (dist[j] != 0 && dist[j] < min)
			{
				k = j;
				min = dist[j];
			}
		printf("第%d条最短路径长度为%d--", ++n, min);

		for (j = 0; j < G.vexNum; j++)
			if (path[k][j] != -1)
				printf("%c-->", G.vexs[path[k][j]]);
		printf("%c ", G.vexs[k]);
		printf("\n");

		for (j = 0; j < G.vexNum; j++)
			if (j != k && dist[j] != 0)
				if ((dist[k] + G.arcs[k][j]) < dist[j])
				{
					dist[j] = dist[k] + G.arcs[k][j];
					for (m = 0; m < G.vexNum; m++)
						path[j][m] = path[k][m];
					for (m = 0; m < G.vexNum && path[j][m] != -1;)
						m++;
						path[j][m] = k;
				}
		dist[k] = 0;
		flag = 0;
		for (j = 0; j < G.vexNum; j++)
			if (dist[j] != 0 && dist[j] < 10000)
				flag = 1;
	}//while
}

/*
	哈密尔顿链
	*/
void hamilton(MGraph G, int visited[])
{
	register int i, j = 0;
	int path[MAXSIZE];
	for (i = 0; i < G.vexNum; i++)
		visited[i] = 0;

	int n = 0;
	for (i = 0; i < G.vexNum; i++)
		if (!visited[i])
			DFS(G, i, path, visited, &n);
}

//寻径
void DFS(MGraph G, int i, int path[], int visited[], int* n)
{
	register int j;
	visited[i] = 1;
	path[*n] = i;
	(*n)++;
	if ((*n) == G.vexNum)
		for (j = 0; j < G.vexNum; j++)
			printf("%c ", G.vexs[j]);

	for (j = 0; j < G.vexNum; j++)
		if (G.arcs[i][j] > 0 && G.arcs[i][j] < 10000 && !visited[j])
		{
			i = j;
			DFS(G, i, path, visited, n);
		}
			
	visited[i] = 0;
	(*n)--;
}

/**
 *
 *深度遍历
 */

void dfTraverAlg(ALGraph G, int v, int visited[])
{
	ArcNode* p = G.adjList[v].firstArc;
	printf("%c", G.adjList[v]);
	visited[v] = 1;
	while (p)
	{
		if (visited[p->adjvex] == 0)
			dfTraverAlg(G, p->adjvex, visited);
		p = p->nextArc;
	}
}

//非连通图
void dfTraverUnaccessAlg(ALGraph G, int visited[])
{
	int v;
	flushVisited(visited, G.vexNum);
	for (v = 0; v < G.vexNum; v++)
		if (!visited[v])
			dfTraverAlg(G, v, visited);
	printf("\n");
}

/**
 *
 *广度遍历
 */

void bfTraverAlg(ALGraph G, int v, int visited[])
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

//非连通图
void bfTraverUnaccessAlg(ALGraph G, int visited[])
{
	int v;
	flushVisited(visited, G.vexNum);
	for (v = 0; v < G.vexNum; v++)
		if (!visited[v])
			bfTraverAlg(G, v, visited);
	printf("\n");
}

/*
 * 深度遍历
 */
void dfTraverMg(MGraph G, int v, int visited[])
{
	register int i;
	printf("%c", G.vexs[v]);
	visited[v] = 1;
	for (i = 0; i < G.vexNum; i++)
		if (G.arcs[v][i] > 0 && !visited[i])
			dfTraverMg(G, i, visited);
}

//非连通图
void dfTraverUnaccessMg(MGraph G, int visited[])
{
	int v = 0;
	flushVisited(visited, G.vexNum);
	for (v = 0; v < G.vexNum; v++)
		if (!visited[v])
			dfTraverMg(G, v, visited);
	printf("\n");
}

/*
 * 广度遍历
 */
void bfTraverMg(MGraph G, int v, int visited[])
{
	register int i;
	int w;
	Queue q;
	initQueue(&q);
	visited[v] = 1;
	printf("%c", G.vexs[v]);
	enQueue(&q, v);
	while (!queueEmpty(q))
	{
		deQueue(&q, &w);
		for (i = 0; i < G.vexNum; i++)
			if (G.arcs[w][i] > 0 && !visited[i])
			{
				enQueue(&q, i);
				printf("%c", G.vexs[i]);
				visited[i] = 1;
			}
	}
}

//非连通图
void bfTraverUnaccessMg(MGraph G, int visited[])
{
	int v = 0;
	flushVisited(visited, G.vexNum);
	for (v = 0; v < G.vexNum; v++)
		if (!visited[v])
			bfTraverMg(G, v, visited);
	printf("\n");
}
/**
 *
 *队列初始化
 */

int initQueue(Queue* Q)
{
	Q->front = 0;
	Q->rear = 0;
	Q->queueSize = MAXSIZE;
	return 1;
}

/**
 *
 *判断队空
 */

int queueEmpty(Queue Q)
{
	if (Q.front == Q.rear)
		return 1;
	else
		return 0;
}

/**
 *
 *进队列
 */

int enQueue(Queue* Q, int v)
{
	if ((Q->rear + 1) % Q->queueSize == Q->front)
		return 0;
	Q->data[Q->rear] = v;
	Q->rear = (Q->rear + 1) % Q->queueSize;
	return 1;
}

/**
 *
 *出队列
 */

int deQueue(Queue* Q, int* v)
{
	if (Q->front == Q->rear)
		return 0;
	*v = Q->data[Q->front];
	Q->front = (Q->front + 1) % Q->queueSize;
	return 1;
}