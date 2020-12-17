function readFile(file) {
  return new Promise(function (resolve) {
    const reader = new FileReader();

    // This is called when finished reading
    reader.onload = function (event) {
      // Return an array with one image
      if (file.type.indexOf("image/") === 0) {
        const src = `YOUR IMAGE URL`;

        resolve({
          // These are attributes like size, name, type, ...
          lastModifiedDate: file.lastModifiedDate,
          lastModified: file.lastModified,
          name: file.name,
          size: file.size,
          type: file.type,

          // This is the files content as base64
          src//event.target.result
        });
      } else {
        resolve({
          // These are attributes like size, name, type, ...
          lastModifiedDate: file.lastModifiedDate,
          lastModified: file.lastModified,
          name: file.name,
          size: file.size,
          type: file.type,

          // This is the files content as base64
          src: event.target.result
        });
      }

    };

    if (file.type.indexOf('text/') === 0 || file.type === 'application/json') {
      reader.readAsText(file);
    } else if (file.type.indexOf('image/') === 0) {
      reader.readAsDataURL(file);
    } else {
      reader.readAsBinaryString(file);
    }
  });
}

// Read multiple files using above function
export default function readFiles(files) {
  return Promise.all(files.map(readFile));
}