import os,platform

current_dir=os.getcwd()
exe_dir=os.path.join(current_dir,"executables")
c_list=[]

if not os.path.exists(exe_dir):
    os.makedirs(exe_dir)

for i in os.listdir():
    if i.endswith(".c"):
        extension=""
        if platform.system()=="Windows":
            extension=".exe"
        exe_path = os.path.join(exe_dir,i.replace(".c",""))
        c_path = os.path.join(current_dir,i)
        os.system("gcc \"{0}\" -o \"{1}\"".format(c_path,exe_path+extension))
