package com.workintech.sqldmljoins.repository;

import com.workintech.sqldmljoins.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {


    //Kitap alan öğrencilerin öğrenci bilgilerini listeleyin..
    String QUESTION_2 = "SELECT ogrenci.* FROM islem INNER JOIN ogrenci  ON islem.ogrno = ogrenci.ogrno";
    @Query(value = QUESTION_2, nativeQuery = true)
    List<Ogrenci> findStudentsWithBook();


    //Kitap almayan öğrencileri listeleyin.
    String QUESTION_3 = "SELECT ogrenci.* FROM ogrenci LEFT JOIN islem ON islem.ogrno = ogrenci.ogrno WHERE islem.kitapno IS NULL";
    @Query(value = QUESTION_3, nativeQuery = true)
    List<Ogrenci> findStudentsWithNoBook();

    //10A veya 10B sınıfındaki öğrencileri sınıf ve okuduğu kitap sayısını getirin.
    String QUESTION_4 = "SELECT ogrenci.sinif, COUNT(islem.kitapno)  FROM  ogrenci INNER JOIN islem ON islem.ogrno = ogrenci.ogrno INNER JOIN kitap ON kitap.kitapno= islem.kitapno WHERE ogrenci.sinif = '10A' OR ogrenci.sinif = '10B' GROUP BY ogrenci.sinif";
    @Query(value = QUESTION_4, nativeQuery = true)
    List<KitapCount> findClassesWithBookCount();

    //Öğrenci tablosundaki öğrenci sayısını gösterin
    String QUESTION_5 = "SELECT COUNT(ogrenci.ogrno) FROM ogrenci";
    @Query(value = QUESTION_5, nativeQuery = true)
    Integer findStudentCount();

    //Öğrenci tablosunda kaç farklı isimde öğrenci olduğunu listeleyiniz.
    String QUESTION_6 = "SELECT  COUNT(DISTINCT ogrenci.ad) FROM ogrenci";
    @Query(value = QUESTION_6, nativeQuery = true)
    Integer findUniqueStudentNameCount();

    //--İsme göre öğrenci sayılarının adedini bulunuz.
    //--Ali: 2, Mehmet: 3
    String QUESTION_7 = "SELECT o.ad, COUNT(o.ad) from ogrenci o GROUP BY o.ad";
    @Query(value = QUESTION_7, nativeQuery = true)
    List<StudentNameCount> findStudentNameCount();


    String QUESTION_8 = "SELECT o.sinif, COUNT(o.ogrno) from ogrenci o GROUP BY o.sinif\n";
    @Query(value = QUESTION_8, nativeQuery = true)
    List<StudentClassCount> findStudentClassCount();

    String QUESTION_9 = "SELECT o.ad,o.soyad, COUNT(i.kitapno) from ogrenci o INNER JOIN islem i on o.ogrno = i.ogrno GROUP BY o.ad,o.soyad\n";
    @Query(value = QUESTION_9, nativeQuery = true)
    List<StudentNameSurnameCount> findStudentNameSurnameCount();
}
