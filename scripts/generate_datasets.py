# Usage: python3 generate_datasets.py
# Description: This script generates datasets of size 2^i where i is in the range 0 to 6. The datasets are stored in the dataset folder.

import json
import os

os.chdir("dataset")

for i in range(7):
	print(2**i)
	n = 2**i
	a = json.load(open("feed_index.json"))
	b = a[:n]
	json.dump(b, open(f"feed_index_{i}.json", "w"))