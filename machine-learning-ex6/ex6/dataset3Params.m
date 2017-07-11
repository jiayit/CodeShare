function [C, sigma] = dataset3Params(X, y, Xval, yval)
%DATASET3PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = DATASET3PARAMS(X, y, Xval, yval) returns your choice of C and 
%   sigma. You should complete this function to return the optimal C and 
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
C = 1;
sigma = 0.3;

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
%

%my code

%model = svmTrain(X, y, C, @(x1, x2) gaussianKernel(x1, x2, sigma)); 
%error = mean(double(svmPredict(model,Xval) ~= yval));
%error_min = error ;
%a = [0.01 0.03 0.1 0.3 1 3 10 30];
%for i = 1:length(a)
%  C_min = a(i);
%  for j = 1:length(a)
%   sigma_min = a(j);
%   model = svmTrain(X, y, C_min, @(x1, x2) gaussianKernel(x1, x2, sigma_min));
%   error = mean(double(svmPredict(model,Xval) ~= yval));
%   if(error < error_min)
%      C = C_min;
%      sigma = sigma_min;
%      error_min = error;
%   end
 
%  end
  
%end
 

C = 1
sigma = 0.1






% =========================================================================

end
