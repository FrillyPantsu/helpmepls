package org.csc133.a2;

// Steerable interface for objects controlled by the player
// Currently only helicopters are steerable
/*Note that the difference between steerable and moveable is that other objects can
request a change in the heading of steerable objects whereas other objects can only request that a
movable object update its own location according to its current speed and heading. */
public interface ISteerable {
	int speed = 0, heading = 0;
}
