FROM python : 3-9 slim
WORKDIR /app

COPY hello.python


CMD["python", ""hello.py"]