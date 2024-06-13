 #!/bin/sh
set -e
mkdir -p build
mkdir -p build/frontmatter
mkdir -p build/mainmatter
mkdir -p build/backmatter
xelatex -synctex=1 -interaction=nonstopmode -output-directory=build main.tex
makeglossaries -d build "main"
xelatex -synctex=1 -interaction=nonstopmode -output-directory=build main.tex
biber --output_directory build "main"
xelatex -synctex=1 -interaction=nonstopmode -output-directory=build main.tex
xelatex -synctex=1 -interaction=nonstopmode -output-directory=build main.tex
