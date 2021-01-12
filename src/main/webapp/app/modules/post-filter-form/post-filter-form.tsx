
import './post-filter.scss'
import React,{useState, useRef, useEffect} from 'react';
import { AvForm, AvField, AvGroup, AvInput } from 'availity-reactstrap-validation';

import PropTypes from 'prop-types';
import set = Reflect.set;

// PostFilterForm.propTypes = {
//   onSubmit: PropTypes.func,
// };
// PostFilterForm.defaultProps={
//   onSubmit: null
// }
function useComponentVisible(initialIsVisible) {
  const [isComponentVisible, setIsComponentVisible] = useState(
    initialIsVisible
  );
  const ref = useRef(null);

  const handleHideDropdown = (event: KeyboardEvent) => {
    if (event.key === "Escape") {
      setIsComponentVisible(false);
    }
  };

  const handleClickOutside = event => {
    if (ref.current && !ref.current.contains(event.target)) {
      setIsComponentVisible(false);
    }
  };

  useEffect(() => {
    document.addEventListener("keydown", handleHideDropdown, true);
    document.addEventListener("click", handleClickOutside, true);
    return () => {
      document.removeEventListener("keydown", handleHideDropdown, true);
      document.removeEventListener("click", handleClickOutside, true);
    };
  });

  return { ref, isComponentVisible, setIsComponentVisible };
}


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

  const { ref, isComponentVisible ,setIsComponentVisible} = useComponentVisible(true);
  window.console.log(isComponentVisible)
  return (
    <div className="post-filter-form" ref={ref} onClick={() =>setIsComponentVisible(true)}>
      <AvForm>
        <AvGroup>
          <AvInput type="text" name="keyword" value={keyword} onChange={handleSearchTermChange}/>
          {/*<AvInput type="text" name="searchTerm" value={searchTerm} onChange={handleSearchTermChange} />*/}
        </AvGroup>
      </AvForm>
      <div className={keyword?"suggest-search":"none-suggest-search"} >
          {!isComponentVisible&&(
            <div className="none-suggest-search"></div>
          )}
          {isComponentVisible&&(
            <div className="list-result" id="search-result">
              {postList.length>0?(
                <ul>
                  {postList.map((item, index) =>(
                    <li key={index*101+item.id*102} dangerouslySetInnerHTML={{__html:item.tensanpham}}></li>
                  ))}
                </ul>):(<div>Khong tim thay ket qua</div>)}
            </div>
          )}
        {/*<div className="list-result">*/}
        {/*  {postList.length>0?(*/}
        {/*    <ul>*/}
        {/*      {postList.map((item, index) =>(*/}
        {/*        <li key={index*101+item.id*102} dangerouslySetInnerHTML={{__html:item.tensanpham}}></li>*/}
        {/*      ))}*/}
        {/*    </ul>):(<div>Khong tim thay ket qua</div>)}*/}
        {/*</div>*/}
      </div>
    </div>
  );
}
export default PostFilterForm;