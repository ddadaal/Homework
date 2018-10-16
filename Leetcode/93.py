class Solution:

    def getPart(self, str):
        possible = []
        # one digit
        if len(str)<1:
            return possible
        possible.append((str[1:], str[0]))

        # two digit
        if len(str)<2:
            return possible
        if str[0] != '0':
            possible.append((str[2:], str[0:2]))
        
        # three digit
        if len(str)<3:
            return possible
        num = int(str[0:3])
        if 100 <= num and num <= 255:
            possible.append((str[3:], str[0:3]))
        
        return possible


    def restoreIpAddresses(self, s):
        """
        :type s: str
        :rtype: List[str]
        """
        result = set()
        for left1, part1 in self.getPart(s):
            for left2, part2 in self.getPart(left1):
                for left3, part3 in self.getPart(left2):
                    for left4, part4 in self.getPart(left3):
                        # print(left1, part1, left2, part2, left3, part3, left4, part4)
                        if len(part1) + len(part2) + len(part3) + len(part4) == len(s):
                            result.add("{}.{}.{}.{}".format(part1, part2, part3, part4))

        return list(result)

s = Solution()
print(s.restoreIpAddresses("010010"))