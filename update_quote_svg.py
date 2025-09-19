import requests
import xml.etree.ElementTree as ET


def update_svg_file(svg_path, quote, author):
    try:
        # Load the SVG file
        tree = ET.parse(svg_path)
        root = tree.getroot()

        # Find the <div> with id="quote" inside <foreignObject>
        for foreign_object in root.findall(".//{http://www.w3.org/2000/svg}foreignObject"):
            for div in foreign_object.findall(".//{http://www.w3.org/1999/xhtml}div[@id='quote']"):
                div.text = quote  # Update the text content

        author_elem = root.find(".//*[@id='author']")
        if author_elem is not None:
            author_elem.text = f"— {author}"

        # Save the updated SVG file
        tree.write(svg_path)
    except Exception as e:
        print(f"An error occurred while processing {svg_path}: {str(e)}")

try:
    # Fetch a random quote from ZenQuotes API
    url = "https://zenquotes.io/api/random"
    response = requests.get(url)
    if response.status_code == 200:
        data = response.json()
        quote = data[0]["q"]
        author = data[0]["a"]
    else:
        quote = "Failed to load quote"
        author = ""

    # Update both SVG files
    update_svg_file("quote_background_dark.svg", quote, author)
    update_svg_file("quote_background_light.svg", quote, author)

except FileNotFoundError:
    print(f"Error: The file was not found")
except Exception as e:
    print(f"An error occurred: {str(e)}")