import csv
import sys
import json
import os



if __name__ == '__main__':
    args = sys.argv
    #if (len(args) < 2):
     #   print(
      #      'Incorrect usage: please supply at least one argument specifying the input csv\nThe second optional argument specifies the name of the output json file')
    for filename in os.listdir("C:/Users/akshayd/spark-play/app/demo/"):
        if filename.endswith(".csv"):
            csv_file = os.path.abspath(filename)
            json_file = csv_file.replace('csv','json')
            with open(csv_file) as csvf:
                json_raw = {'data':[]}
                csv_reader = csv.reader(csvf, delimiter=',')
                headers = next(csv_reader)
                print(headers)
                for row in csv_reader:
                    row_dict = {}
                    for i, cell in enumerate(row):
                        row_dict[headers[i]] = cell
                    json_raw['data'].append(row_dict)
                json_str = json.dumps(json_raw)
                f = open(json_file, 'w')
                f.write(json_str)
                f.close()
