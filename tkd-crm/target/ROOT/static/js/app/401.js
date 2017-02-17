var unauthApp = angular.module('unauthApp',['ngRoute']);

unauthApp.controller('unauthController',['$rootScope','$scope','$location',function($rootScope,$scope,$location){
	if($rootScope.action==undefined){
		$rootScope.action='#/';
	}
}]);

