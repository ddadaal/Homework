import os

current_dir=os.getcwd()
exe_dir=os.path.join(current_dir,"executables")
c_list=[]

for i in os.listdir():
    if i.endswith(".c"):
        exe_path = os.path.join(exe_dir,i+".exe")
        c_path = os.path.join(current_dir,i)
        os.system("gcc \"{0}\" -o \"{1}\"".format(c_path,exe_path))