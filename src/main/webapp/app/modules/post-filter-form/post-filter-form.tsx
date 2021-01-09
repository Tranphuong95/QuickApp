import React,{useState, useRef} from 'react';
import { AvForm, AvField, AvGroup, AvInput } from 'availity-reactstrap-validation';

import PropTypes from 'prop-types';

// PostFilterForm.propTypes = {
//   onSubmit: PropTypes.func,
// };
// PostFilterForm.defaultProps={
//   onSubmit: null
// }
function PostFilterForm(props)
{
  const {onSubmit}=props;
  const [searchTerm, setSearchTerm]=useState('')
  const typingTimeoutRef=useRef(null)
  const handleSearchTermChange=(e)=>{
    const value= e.target.value
    setSearchTerm(value);
    if(!onSubmit) return;
    if(typingTimeoutRef.current){
      clearTimeout(typingTimeoutRef.current)
    }
    typingTimeoutRef.current=setTimeout(()=>{

      const formValue={searchTerm:value}
      onSubmit(formValue)
    }, 300);
  }
  return (
    <AvForm>
      <AvGroup>
        {/*<AvInput/>*/}
        <AvInput type="text" name="searchTerm" value={searchTerm} onChange={handleSearchTermChange} />
      </AvGroup>
    </AvForm>
  );
}
export default PostFilterForm;