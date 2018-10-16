class Solution:
    def spiralOrder(self, matrix):
        """
        :type matrix: List[List[int]]
        :rtype: List[int]
        """
        if len(matrix) ==0:
            return []
        m = len(matrix[0])
        n = len(matrix)
        barrier = []
        for i in range(n+2):
            l = []
            for j in range(m+2):
                l.append(0)
            barrier.append(l)
        
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
        point = [-1,0]
        direction = 'r'
        result = []
        while len(result) < m*n:
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
            result.append(matrix[point[1]][point[0]])
        
        return result

s = Solution()
print(s.spiralOrder([[1,2,3],[4,5,6],[7,8,9]]))