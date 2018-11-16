package br.com.libshare.friends;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendsRepository extends JpaRepository<FriendsEntity, FriendsKey> {

}