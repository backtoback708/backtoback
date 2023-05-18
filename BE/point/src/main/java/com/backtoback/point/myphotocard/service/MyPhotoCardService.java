package com.backtoback.point.myphotocard.service;

import com.backtoback.point.myphotocard.domain.MyPhotoCard;

import java.util.List;

public interface MyPhotoCardService {
    void createMyPhotocard(MyPhotoCard myPhotoCard);

    List<MyPhotoCard> getMyPhotocardList(Long memberSeq);
}
