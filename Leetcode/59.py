def create_matrix(m, n):
    matrix = []
    for i in range(n):
        l = []
        for j in range(m):
            l.append(0)
        matrix.append(l)
    return matrix

class Solution:
    def generateMatrix(self, n):
        """
        :type matrix: List[List[int]]
        :rtype: List[int]
        """
        if n ==0:
            return []
        m = n
        n = n

        matrix = create_matrix(m,n)

        barrier = create_matrix(m+2, n+2)
        
        # set initial barrier
        # top
        barrier[0] = [1]*(m+2)
        # bottom
        barrier[n+1] = [1]*(m+2)
        # left and right
        for i in range(n+2):
            barrier[i][0] = 1
            barrier[i][m+1] = 1
        
        

        # traverse
        count = 1
        point = [-1,0]
        direction = 'r'
        while count <= m*n:
            if direction == 'r':
                point[0]+=1
                if barrier[point[1]+1][point[0]+2]:
                    direction = 'd'
            elif direction == 'd':
                point[1]+=1
                if barrier[point[1]+2][point[0]+1]:
                    direction = 'l'
            elif direction == 'l':
                point[0]-=1
                if barrier[point[1]+1][point[0]]:
                    direction = 'u'
            else: # u
                point[1]-=1
                if barrier[point[1]][point[0]+1]:
                    direction = 'r'
            barrier[point[1]+1][point[0]+1]=1
            matrix[point[1]][point[0]]= count
            count+=1
        
        return matrix

s = Solution()
print(s.generateMatrix(3))