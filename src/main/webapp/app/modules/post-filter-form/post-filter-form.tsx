import './post-filter.scss'
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
  const {postList}=props;
  window.console.log(postList)
  // const [searchTerm, setSearchTerm]=useState('')
  const [keyword, setKeyword]=useState('')
  const typingTimeoutRef=useRef(null)

  const handleSearchTermChange=(e)=>{
    const value= e.target.value
    // setSearchTerm(value);
    setKeyword(value)
    if(!onSubmit) return;
    if(typingTimeoutRef.current){
      clearTimeout(typingTimeoutRef.current)
    }
    typingTimeoutRef.current=setTimeout(()=>{

      // const formValue={searchTerm:value}
      const formValue={keyword:value}
      onSubmit(formValue)
    }, 300);
  }
  return (
    <div>
      <AvForm>
        <AvGroup>
          <AvInput type="text" name="keyword" value={keyword} onChange={handleSearchTermChange} />
          {/*<AvInput type="text" name="searchTerm" value={searchTerm} onChange={handleSearchTermChange} />*/}
        </AvGroup>
      </AvForm>
      <div className={keyword?"suggest-search":"none-suggest-search"}>
        <div className="list-result">
          {postList.length>0?(<ul>
            {postList.map((item, index) =>(
              <li key={index*101+item.id*102} dangerouslySetInnerHTML={{__html:item.tensanpham}}/>
            ))}
          </ul>):(<div>Khong tim thay ket qua</div>)}
        </div>
      </div>
    </div>
  );
}
export default PostFilterForm;