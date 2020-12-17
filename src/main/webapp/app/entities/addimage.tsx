// // import { EditorState, AtomicBlockUtils } from 'draft-js';
// //
// // export default (
// //   editorState: EditorState,
// //   url: string,
// //   extraData: Record<string, unknown>
// // ): EditorState => {
// //   const urlType = 'IMAGE';
// //   const contentState = editorState.getCurrentContent();
// //   const contentStateWithEntity = contentState.createEntity(
// //     urlType,
// //     'IMMUTABLE',
// //     { ...extraData, src: url }
// //   );
// //   const entityKey = contentStateWithEntity.getLastCreatedEntityKey();
// //   const newEditorState = AtomicBlockUtils.insertAtomicBlock(
// //     editorState,
// //     entityKey,
// //     ' '
// //   );
// //   return EditorState.forceSelection(
// //     newEditorState,
// //     newEditorState.getCurrentContent().getSelectionAfter()
// //   );
// // };
//
// class MyUploadAdapter {
//   constructor( loader ) {
//     // The file loader instance to use during the upload.
//     this.loader = loader;
//   }
//
//   // Starts the upload process.
//   upload() {
//     return this.loader.file
//       .then( file => new Promise( ( resolve, reject ) => {
//
//         const toBase64 = file => new Promise((resolve, reject) => {
//           const reader = new FileReader();
//           reader.readAsDataURL(file);
//           reader.onload = () => resolve(reader.result);
//           reader.onerror = error => reject(error);
//         });
//
//         return toBase64(file).then(cFile=>{
//           // console.log(cFile)
//           return  fetch("admin/uploadimage", {
//             imageBinary: cFile
//           }).then((d) => {
//             if (d.status) {
//               console.log(d)
//               this.loader.uploaded = true;
//               console.log(d.url)
//               resolve( {
//                 default:Response.url
//               } );
//             } else {
//               reject(`Couldn't upload file: ${ file.name }.`)
//             }
//           });
//         })
//
//       } ) );
//   }
//
//
// }
//
// // ...
//
// export default function MyCustomUploadAdapterPlugin( editor ) {
//   editor.plugins.get( 'FileRepository' ).createUploadAdapter = ( loader ) => {
//     // Configure the URL to the upload script in your back-end here!
//     return new MyUploadAdapter( loader );
//   };
// }