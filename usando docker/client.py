import requests
import time

URL = "http://localhost:8080"

for i in range(20):
    r = requests.get(URL)
    print(f"{i+1:02d} → {r.text}")
    time.sleep(0.2)
