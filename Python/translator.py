import re
import sys

if len(sys.argv) < 1:
    print('Usage: "translator.py <object file> [output file]')

with open(sys.argv[1], 'r') as file:
    data = file.read()

pattern = r'"from": \[([\d.]+), ([\d.]+), ([\d.]+)\],\s*"to": \[([\d.]+), ([\d.]+), ([\d.]+)\]'

matches = re.findall(pattern, data)

lines = ["//Code generate by Kylstreak12's object file translator\n"]

i = 1
for match in matches:
    line = f'VoxelShape obj{i} = VoxelShapes.cuboid('
    for item in match:
        if item != '0':
            item = item + "/16"
        line += item + "f, "
    line = line[:-2]
    line += ");"
    i += 1
    lines.append(line + "\n")

line = "VoxelShapes.union("
for index in range(len(lines) - 1):
    line += f'obj{index + 1}, '

line = line[:-2] + ');'

if len(sys.argv) > 2:
    with open(sys.argv[2], 'w') as file:
        file.write(''.join(lines) + f'\n{line}')

else:
    print(''.join(lines) + f'\n{line}')
