package com.codingbox.mallapi.dto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class PageResponseDTO<E> {

   // 목록
   private List<E> dtoList;
   
   // 페이지 처리를 미리 해서 전달
   private List<Integer> pageNumberList;
   
   // 검색 조건, 현재 페이지 정보
   private PageRequestDTO pageRequestDTO;
   
   // 이전, 다음
   private boolean prev;
   private boolean next;
   
   private int totalCount, prevPage, nextPage, totalPage, current;
   
   public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long total) {
      this.dtoList = dtoList;
      this.pageRequestDTO = pageRequestDTO;
      this.totalCount = (int) total;
      
      // 끝 페이지부터 계산
      int end = (int) Math.ceil((pageRequestDTO.getPage()/10.0)) * 10;
      
      // 시작값은 고정
      int start = end - 9;
      
      // 진짜 마지막 페이지값
      int last = (int) Math.ceil(totalCount/(double)pageRequestDTO.getSize());
      
      end = end > last ? last : end;
      
      this.prev = start > 1;
      this.next = totalCount > end * pageRequestDTO.getSize();
      
      /*
       * IntStream.rangeClosed(start, end)
       * 	-> start 1, end 5 : 1,2,3,4,5
       * 	.boxed()
       * 	-> Integer 객체로 박싱
       * 	.collect(Collectors.toList());
       * 	-> ArrayList로 수집
       */
      
      this.pageNumberList = IntStream.rangeClosed(start,end)
    		  .boxed()
    		  .collect(Collectors.toList());
      
      
      // prevPage(이전 페이지 번호)
      this.prevPage = prev ? start -1 : 0;
      this.nextPage = next ? end + 1 : 0;
      
      
      
      
   }
}