def reverseString(str):
    return str[::-1]

class Solution:

    def stringAdd(self, num1, num2):
        add1 = reverseString(num1)
        add2 = reverseString(num2)

        # add 0

        if len(add1) < len(add2):
            add1 += "0"*(len(add2)-len(add1))
        elif len(add2) < len(add1):
            add2 += "0"*(len(add1)-len(add2))

        carry = 0
        result = ""
        for i in range(0, len(add1)):
            digit1=ord(add1[i])-ord("0")
            digit2=ord(add2[i])-ord("0")
            r=digit1+digit2+carry
            carry = r // 10
            result += str(r % 10)
        
        if carry == 1:
            result += "1"
        return reverseString(result)

    def longMultiplyDigit(self, num1, digit):
        digit = int(digit)
        result = ""
        multi1 = reverseString(num1)
        carry = 0
        for c in multi1:
            digit1 = int(c)
            r = digit * digit1 + carry
            carry = r // 10
            result += str(r % 10)
        if carry != 0:
            result += str(carry)
        return reverseString(result)

    def multiply(self, num1, num2):
        """
        :type num1: str
        :type num2: str
        :rtype: str
        """

        if num1 == '0' or num2 == '0':
            return '0'

        result = "0"
        for i in range(len(num2)):
            r = self.longMultiplyDigit(num1, num2[len(num2)-1-i])
            result = self.stringAdd(result, r + "0"*i)
        
        # remove leading 0
        for i in range(len(result)-1):
            if result[i] != '0':
                break
            if result[i]=='0' and result[i+1]!='0':
                result = result[i+1:]
                break


        return result

s = Solution()
print(s.multiply("123","456"))